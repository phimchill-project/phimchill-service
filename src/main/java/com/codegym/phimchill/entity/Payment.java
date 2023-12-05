package com.codegym.phimchill.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PAYMENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "BANK_CODE")
    private String bankCode;
    @Column(name = "BANK_TRANSACTION_NO")
    private String bankTranNo;
    @Column(name = "PAY_DATE")
    private String payDate;
    @Column(name = "TRANSACTION_STATUS")
    private String transactionStatus;
    @Column(name = "PAYMENT_CODE")
    private String paymentCode;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;
}
