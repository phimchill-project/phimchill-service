package com.codegym.phimchill.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String name ;
    private String token;
    private boolean isMember;
    private String role;
}
