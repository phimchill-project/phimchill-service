package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.dto.PaymentDto;
import com.codegym.phimchill.dto.payload.request.ResultPaymentRequest;
import com.codegym.phimchill.dto.payload.response.ResultPaymentResponse;
import com.codegym.phimchill.entity.Payment;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.repository.PaymentRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResultPaymentResponse save(String email, ResultPaymentRequest paymentRequest) throws Exception {
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new Exception("Cannot find user to save payment");
        }
        if (paymentRequest.getTransactionStatus() == "00"){
            user.setMember(true);
            userRepository.save(user);
        }
        Payment payment = Payment.builder()
                .bankCode(paymentRequest.getBankCode())
                .bankTranNo(paymentRequest.getBankTranNo())
                .payDate(paymentRequest.getPayDate())
                .transactionStatus(paymentRequest.getTransactionStatus())
                .paymentCode(paymentRequest.getPaymentCode())
                .user(user)
                .build();
        payment = paymentRepository.save(payment);
        PaymentDto paymentDto = PaymentDto.builder()
                .id(payment.getId())
                .bankCode(payment.getBankCode())
                .bankTranNo(payment.getBankTranNo())
                .payDate(payment.getPayDate())
                .paymentCode(payment.getPaymentCode())
                .build();
        return ResultPaymentResponse.builder()
                .data(paymentDto)
                .message("Save payment success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
