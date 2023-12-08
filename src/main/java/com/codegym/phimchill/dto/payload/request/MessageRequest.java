package com.codegym.phimchill.dto.payload.request;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MessageRequest {
    private String content;
    private Timestamp dateSend;
    private Long replyToUserId;
}
