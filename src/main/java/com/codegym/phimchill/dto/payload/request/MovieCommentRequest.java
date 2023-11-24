package com.codegym.phimchill.dto.payload.request;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieCommentRequest {
    private String comment;
    private Date datePost;
    private Long movieID;
}
