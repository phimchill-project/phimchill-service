package com.codegym.phimchill.dto;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieCommentDto {
    private Long id;
    private String comment;
    private Date datePost;
    private Long movieID;
    private UserDto userDto;
}
