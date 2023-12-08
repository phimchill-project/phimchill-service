package com.codegym.phimchill.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieCommentDto {
    private Long id;
    private String comment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Timestamp datePost;
    private Long movieId;
    private UserDto userDto;
    private List<Long> listUserIdLiked;
    private int totalLike;
    private List<MovieSubCommentDto> subCommentDtoList;
    private List<UserDto> listUserTagged;
}
