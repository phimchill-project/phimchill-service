package com.codegym.phimchill.controller.auth;

import com.codegym.phimchill.configuration.VnPayConfiguration;
import com.codegym.phimchill.dto.payload.request.CheckPayMentRequest;
import com.codegym.phimchill.dto.payload.request.PaymentRequest;
import com.codegym.phimchill.dto.payload.request.ResultPaymentRequest;
import com.codegym.phimchill.dto.payload.response.ListMessageResponse;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.dto.payload.response.PaymentResponse;
import com.codegym.phimchill.dto.payload.response.ResultPaymentResponse;
import com.codegym.phimchill.entity.Payment;
import com.codegym.phimchill.service.PaymentService;
import com.codegym.phimchill.service.SecurityService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private SecurityService securityService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest,
                                                         @RequestHeader ("Authorization") final String authToken,
                                                         HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            PaymentResponse response = PaymentResponse.builder()
                    .data(null)
                    .message("Responding with unauthorized error. Message - {}")
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = paymentRequest.getAmount()*100;
        String bankCode = paymentRequest.getBankCode();

        String vnp_TxnRef = VnPayConfiguration.getRandomNumber(8);
        String vnp_IpAddr = VnPayConfiguration.getIpAddress(req);

        String vnp_TmnCode = VnPayConfiguration.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = req.getParameter("language");
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", VnPayConfiguration.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        vnp_Params.put("vnp_ReturnUrl", VnPayConfiguration.vnp_ReturnUrl+"?email="+ email);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VnPayConfiguration.hmacSHA512(VnPayConfiguration.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VnPayConfiguration.vnp_PayUrl + "?" + queryUrl;
        PaymentResponse response = PaymentResponse.builder()
                .data(paymentUrl)
                .message("Creatte Payment Success")
                .statusCode(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @GetMapping("/payment-callback")
//    public void paymentCallback(@RequestParam Map<String, String> queryParams, HttpServletResponse response) throws IOException {
//        String emailCurrent = queryParams.get("email");
//        String bankCode = queryParams.get("vnp_BankCode");
//        String bankTranNo = queryParams.get("vnp_BankTranNo");
//        String payDate = queryParams.get("vnp_PayDate");
//        String transactionStatus = queryParams.get("vnp_TransactionStatus");
//        String paymentCode = queryParams.get("vnp_TxnRef");
//        Payment payment = Payment.builder()
//                .bankCode(bankCode)
//                .bankTranNo(bankTranNo)
//                .payDate(payDate)
//                .transactionStatus(transactionStatus)
//                .paymentCode(paymentCode)
//                .build();
//        paymentService.save(emailCurrent, payment);
//    }

    @PostMapping("/save")
    public ResponseEntity<ResultPaymentResponse> paymentCallback(@RequestHeader("Authorization") final String authToken, @RequestBody ResultPaymentRequest resultPaymentRequest){
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            ResultPaymentResponse response = ResultPaymentResponse.builder()
                    .data(null)
                    .message("Responding with unauthorized error. Message - {}")
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            ResultPaymentResponse response = paymentService.save(email, resultPaymentRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ResultPaymentResponse response = ResultPaymentResponse.builder()
                    .data(null)
                    .message(e.getMessage())
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
}
