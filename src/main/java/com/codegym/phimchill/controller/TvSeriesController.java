package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.dto.payload.response.FindTvSeriesRponse;
import org.springframework.web.bind.annotation.*;
import com.codegym.phimchill.dto.payload.response.TvSeriesResponse;
import com.codegym.phimchill.service.TvSeriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @GetMapping("/search")
    public ResponseEntity<?> getByName(/*@RequestHeader("Authorization") final String authToken,*/ @RequestParam(value = "name", required = true) String nameMovie) {
        /*if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
        }*/
        TvSeriesDto tvSeriesDto = tvSeriesService.findByName(nameMovie);
        FindTvSeriesRponse response;
        if (tvSeriesDto != null){
            response = FindTvSeriesRponse.builder()
                    .data(tvSeriesDto)
                    .statusCode(HttpStatus.OK.value())
                    .message("Success")
                    .build();
        }else {
            response = FindTvSeriesRponse.builder()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .message("Not found TvSeries")
                    .build();
        }
        return ResponseEntity.ok(response);
    }
}
