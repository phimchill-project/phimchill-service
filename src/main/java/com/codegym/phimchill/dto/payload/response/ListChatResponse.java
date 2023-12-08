package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.ChatDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ListChatResponse {
    private List<ChatDto> data;
    private String message;
    private int statusCode;
}
