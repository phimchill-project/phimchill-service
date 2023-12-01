package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.payload.request.TvSeriesRequest;
import com.codegym.phimchill.entity.TVSeries;
import com.codegym.phimchill.service.UserService;
import org.apache.logging.log4j.message.Message;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.dto.payload.response.FindTvSeriesRponse;
import org.springframework.web.bind.annotation.*;
import com.codegym.phimchill.dto.payload.response.TvSeriesResponse;
import com.codegym.phimchill.service.TvSeriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
    @GetMapping("/favorites")
    public ResponseEntity<TvSeriesResponse> findFavoriteList(@RequestParam Long user_id){
        TvSeriesResponse tvSeriesResponse = new TvSeriesResponse();
        List<TvSeriesDto> tvSeriesList = tvSeriesService.getTop10FavoriteList(user_id);
        System.out.println("************************"+tvSeriesList);
        tvSeriesResponse.setListTVSeries(tvSeriesService.getTop10FavoriteList(user_id));
        tvSeriesResponse.setTitle("Favorite TVseries List");
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
    @PostMapping("/addFavoriteList")
    public ResponseEntity<String> addFavoriteList(@RequestBody TvSeriesRequest tvSeriesRequest){

        String message = tvSeriesService.addFavoriteList(tvSeriesRequest.getUser_id(),tvSeriesRequest.getTvSeries_id());
        return new ResponseEntity<>(message, HttpStatus.OK) ;
    }
}
