package com.codegym.phimchill.dto;

import lombok.*;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieCommentDto {
    private Long id;
    private String comment;
    private Date datePost;
    private Long movieId;
    private UserDto userDto;
    private List<MovieSubCommentDto> subCommentDtoList;
    private List<UserDto> listUserTagged;
}
