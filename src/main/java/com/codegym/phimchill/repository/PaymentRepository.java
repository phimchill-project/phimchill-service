package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
