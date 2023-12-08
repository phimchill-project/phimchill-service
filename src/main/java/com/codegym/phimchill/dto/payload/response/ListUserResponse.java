package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.UserDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ListUserResponse {
    private List<UserDto> data;
    private String message;
    private int statusCode;
}
