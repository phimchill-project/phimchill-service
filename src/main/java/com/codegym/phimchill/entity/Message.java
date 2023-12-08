package com.codegym.phimchill.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "MESSAGE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "DATE_SEND")
    private Timestamp dateSend;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User userSend;
    @ManyToOne
    @JoinColumn(name = "CHAT_ID", referencedColumnName = "ID")
    private Chat chat;
}
