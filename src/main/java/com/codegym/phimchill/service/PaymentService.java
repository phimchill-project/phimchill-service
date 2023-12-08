package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.request.ResultPaymentRequest;
import com.codegym.phimchill.dto.payload.response.ResultPaymentResponse;
import com.codegym.phimchill.entity.Payment;

public interface PaymentService {

    ResultPaymentResponse save(String email, ResultPaymentRequest request) throws Exception;
}
