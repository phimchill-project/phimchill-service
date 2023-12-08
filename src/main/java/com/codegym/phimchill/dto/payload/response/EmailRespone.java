package com.codegym.phimchill.dto.payload.response;

import com.codegym.phimchill.dto.Email;
import com.codegym.phimchill.dto.MovieDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmailRespone {
    private List<Email> data ;
    private String message;
    private int statusCode;
}
