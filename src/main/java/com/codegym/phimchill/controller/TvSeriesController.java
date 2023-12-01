package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.payload.response.FindManyTvSeriesReponse;
import com.codegym.phimchill.dto.payload.response.FindMoviesReponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.dto.payload.response.FindTvSeriesReponse;
import org.springframework.web.bind.annotation.*;
import com.codegym.phimchill.dto.payload.response.TvSeriesResponse;
import com.codegym.phimchill.service.TvSeriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tvseries")
@CrossOrigin(value = "*", maxAge = 3600)
public class TvSeriesController {
    private final TvSeriesService tvSeriesService;

    public TvSeriesController(TvSeriesService tvSeriesService) {
        this.tvSeriesService = tvSeriesService;
    }

    @GetMapping("/imdb/top10")
    public ResponseEntity<TvSeriesResponse> findTop10ByImdb(){
        TvSeriesResponse tvSeriesResponse = new TvSeriesResponse();
        tvSeriesResponse.setListTVSeries(tvSeriesService.getTop10ByImdb());
        tvSeriesResponse.setTitle("Top TV series");
        return new ResponseEntity<>(tvSeriesResponse, HttpStatus.OK);
    }
    @GetMapping("/newest/top10")
    public ResponseEntity<TvSeriesResponse> findTop10Newest(){
        TvSeriesResponse tvSeriesResponse = new TvSeriesResponse();
        tvSeriesResponse.setListTVSeries(tvSeriesService.getTop10Newest());
        tvSeriesResponse.setTitle("Newest TV series");
        return new ResponseEntity<>(tvSeriesResponse, HttpStatus.OK) ;
    }
    // upcoming => them truong status (isDeleted, UpComing,...) vao bang TvSeries
    //

    @GetMapping("/search")
    public ResponseEntity<?> getByName(/*@RequestHeader("Authorization") final String authToken,*/ @RequestParam(value = "name", required = true) String nameMovie, @RequestParam (value = "type", required = false) String type) {
        /*if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
        }*/
        if ("all".equals(type)){
            List<Optional<TvSeriesDto>> tvSeriesDtos = tvSeriesService.findManyTvSeriesByName(nameMovie);
            FindManyTvSeriesReponse response;
            if (!tvSeriesDtos.isEmpty()){
                response = FindManyTvSeriesReponse.builder()
                        .data(tvSeriesDtos)
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .build();
            }else {
                response = FindManyTvSeriesReponse.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Not found Movies")
                        .build();
            }
            return ResponseEntity.ok(response);
        }else {
            TvSeriesDto tvSeriesDto = tvSeriesService.findByName(nameMovie);
            FindTvSeriesReponse response;
            if (tvSeriesDto != null){
                response = FindTvSeriesReponse.builder()
                        .data(tvSeriesDto)
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .build();
            }else {
                response = FindTvSeriesReponse.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Not found TvSeries")
                        .build();
            }
            return ResponseEntity.ok(response);
        }
    }
}
