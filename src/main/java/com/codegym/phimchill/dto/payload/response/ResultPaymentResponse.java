package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.PaymentDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResultPaymentResponse {
    private PaymentDto data;
    private String message;
    private int statusCode;
}
