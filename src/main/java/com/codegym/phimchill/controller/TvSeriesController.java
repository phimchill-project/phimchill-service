package com.codegym.phimchill.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.codegym.phimchill.dto.payload.response.TvSeriesResponse;
import com.codegym.phimchill.service.TvSeriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tvseries")
public class TvSeriesController {
    private final TvSeriesService tvSeriesService;

    public TvSeriesController(TvSeriesService tvSeriesService) {
        this.tvSeriesService = tvSeriesService;
    }

    @GetMapping("/imdb/top10")
    public ResponseEntity<TvSeriesResponse> findTop10ByImdb(){
        TvSeriesResponse tvSeriesResponse = new TvSeriesResponse();
        tvSeriesResponse.setListTVSeries(tvSeriesService.getTop10ByImdb());
        return new ResponseEntity<>(tvSeriesResponse, HttpStatus.OK);
    }
    @GetMapping("/newest/top10")
    public ResponseEntity<?> findTop10Newest(){
        TvSeriesResponse tvSeriesResponse = new TvSeriesResponse();
        tvSeriesResponse.setListTVSeries(tvSeriesService.getTop10Newest());
        return new ResponseEntity<>(tvSeriesResponse, HttpStatus.OK) ;
    }
    // upcoming => them truong status (isDeleted, UpComing,...) vao bang TvSeries
    //
}
