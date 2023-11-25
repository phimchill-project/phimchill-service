package com.codegym.phimchill.dto;

import com.codegym.phimchill.dto.MovieCommentDto;
import com.codegym.phimchill.dto.UserDto;
import com.codegym.phimchill.entity.RepliedMovieComment;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RepliedMovieCommentDto {
    private Long id;
    private String comment;
    private Long movieCommentId;
    private UserDto userDtoComment;
    private UserDto userDtoSubComment;
}
