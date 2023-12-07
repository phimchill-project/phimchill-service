package com.codegym.phimchill.dto.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NonDataResponse {
    private String message;

    private int statusCode;
}
