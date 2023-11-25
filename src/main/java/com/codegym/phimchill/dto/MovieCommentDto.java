package com.codegym.phimchill.dto;

import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.entity.RepliedMovieComment;
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
    private MovieDto movie;
    private UserDto userDto;
    private List<RepliedMovieCommentDto> repliedMovieCommentDtoList;
}
