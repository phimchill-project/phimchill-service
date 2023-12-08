package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.MessageDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ListMessageResponse {
    private List<MessageDto> data;
    private String message;
    private int statusCode;
}
