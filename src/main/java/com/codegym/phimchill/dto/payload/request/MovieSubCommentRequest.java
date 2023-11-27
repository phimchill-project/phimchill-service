package com.codegym.phimchill.dto.payload.request;

import lombok.*;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieSubCommentRequest {
    private String comment;
    private Date datePost;
    private List<Long> listUserTagged;
}
