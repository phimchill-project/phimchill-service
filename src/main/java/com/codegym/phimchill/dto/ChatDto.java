package com.codegym.phimchill.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatDto {
    private Long id;
    private UserDto userCreated;
    private UserDto replyToUser;
}
