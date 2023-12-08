package com.codegym.phimchill.dto.payload.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResultPaymentRequest {
    private String bankCode;
    private String bankTranNo;
    private String payDate;
    private String transactionStatus;
    private String paymentCode;
}
