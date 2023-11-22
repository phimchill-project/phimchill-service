package com.codegym.phimchill.dto.payload.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieCommentRequest {
    private String comment;
    private Long datePost;
    private Long movieID;
}
