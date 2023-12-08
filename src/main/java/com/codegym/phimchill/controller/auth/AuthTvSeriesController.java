package com.codegym.phimchill.controller.auth;

import com.codegym.phimchill.dto.payload.request.*;
import com.codegym.phimchill.dto.payload.response.NonDataResponse;
import com.codegym.phimchill.service.TvSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/tvSeries")
public class AuthTvSeriesController {

    @Autowired
    private TvSeriesService tvSeriesService;

    @PostMapping()
    private ResponseEntity<?> tvSeries(@RequestBody NewFilmRequest tvSeriesRequest){
        boolean isCreate = tvSeriesService.create(tvSeriesRequest);
        NonDataResponse response;
        if (isCreate){
            response = NonDataResponse.builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Success")
                    .build();
        }else {
            response = NonDataResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message("Fail")
                    .build();
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping
    private ResponseEntity<?> tvSeries(@RequestBody UpdateFilmRequest updateFilmRequest){
        boolean isCreate = tvSeriesService.update(updateFilmRequest);
        NonDataResponse response;
        if (isCreate){
            response = NonDataResponse.builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Success")
                    .build();
        }else {
            response = NonDataResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .message("Fail")
                    .build();
        }
        return ResponseEntity.ok(response);
    }
}