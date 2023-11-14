package com.codegym.phimchill.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterDto {
    private Long id;
    private String email;
    private String name;
}
