package com.codegym.phimchill.dto.payload.response;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MessageResponse {
    private long id;
    private String content;
    private Timestamp dateSend;
//    private String replyToUser;
}
