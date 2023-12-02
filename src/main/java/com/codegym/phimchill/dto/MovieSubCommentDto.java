package com.codegym.phimchill.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieSubCommentDto {
    private Long id;
    private String comment;
    private Long movieCommentId;
    private UserDto userDtoComment;
    private int totalLike;
    private List<Long> listUserIdLiked;
    private List<UserDto> listUserTagged;
}
