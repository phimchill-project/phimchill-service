package com.codegym.phimchill.controller.auth;

import com.codegym.phimchill.service.TvSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/tvseries")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthTvSeriesController {
    @Autowired
    private TvSeriesService tvSeriesService;


}
