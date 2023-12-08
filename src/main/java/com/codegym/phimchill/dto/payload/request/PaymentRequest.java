package com.codegym.phimchill.dto.payload.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentRequest {
    private Long amount;
    private String bankCode;
}
