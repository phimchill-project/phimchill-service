package com.codegym.phimchill.service;

import com.codegym.phimchill.entity.Payment;

public interface PaymentService {

    void save(String email, Payment payment);
}
