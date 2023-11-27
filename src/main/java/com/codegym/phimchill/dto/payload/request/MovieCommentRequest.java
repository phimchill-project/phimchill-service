package com.codegym.phimchill.dto.payload.request;

import lombok.*;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieCommentRequest {
    private String comment;
    private Date datePost;
    private Long movieId;
    private List<Long> listUserTagged;
}
