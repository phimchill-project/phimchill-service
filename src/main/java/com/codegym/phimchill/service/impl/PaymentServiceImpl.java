package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.entity.Payment;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.repository.PaymentRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(String email, Payment payment) {
        User user = userRepository.findUserByEmail(email);
//        if(user == null){
//            throw new Exception("Cannot find user to save payment");
//        }
        payment.setUser(user);
        payment = paymentRepository.save(payment);
    }
}
