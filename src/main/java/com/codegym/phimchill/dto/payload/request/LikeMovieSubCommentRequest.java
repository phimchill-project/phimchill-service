package com.codegym.phimchill.dto.payload.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LikeMovieSubCommentRequest {
    private Long movieSubCommentId;
}
