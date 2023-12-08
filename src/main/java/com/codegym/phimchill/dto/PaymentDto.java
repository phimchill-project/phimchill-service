package com.codegym.phimchill.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentDto {
    private Long id;
    private String bankCode;
    private String bankTranNo;
    private String payDate;
    private String transactionStatus;
    private String paymentCode;
}
