package com.codegym.phimchill.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MessageDto {
    private Long id;
    private String content;
    private Timestamp dateSend;
    private UserDto userSend;
}
