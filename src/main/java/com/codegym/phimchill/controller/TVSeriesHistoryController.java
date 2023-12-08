package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.payload.response.ListTVSeriesHistoryResponse;
import com.codegym.phimchill.service.SecurityService;
import com.codegym.phimchill.service.TVSeriesHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tvseries/history")
@CrossOrigin(value = "*", maxAge = 3600)
public class TVSeriesHistoryController {
    @Autowired
    private TVSeriesHistoryService tvSeriesHistoryService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/watched-tvseries")
    public ResponseEntity<ListTVSeriesHistoryResponse> getWatchedTVSeries(@RequestHeader("Authorization") final String authToken) {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            ListTVSeriesHistoryResponse response = ListTVSeriesHistoryResponse.builder()
                    .data(null)
                    .message("Responding with unauthorized error. Message - {}")
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        try {
            ListTVSeriesHistoryResponse response = tvSeriesHistoryService.getWatchedTVSeries();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ListTVSeriesHistoryResponse response = ListTVSeriesHistoryResponse.builder()
                    .data(null)
                    .message(e.getMessage())
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
