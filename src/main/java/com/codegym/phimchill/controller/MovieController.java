package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.payload.request.FavoriteMoviesRequest;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.response.*;

import com.codegym.phimchill.service.FavoriteMoviesService;
import com.codegym.phimchill.service.MovieService;
import com.codegym.phimchill.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(value = "*", maxAge = 3600)
public class MovieController {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private FavoriteMoviesService favoriteMoviesService;
    @Autowired
    private MovieService movieService;

    @GetMapping("/upcoming")
    public ResponseEntity<?> getUpcomingMovies() {
        ListMovieResponse upcomingMovies = movieService.getUpcomingMovies();
        return ResponseEntity.ok(upcomingMovies);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getMovieDetail(Long id) {
        MovieDto movie = movieService.getMovieById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/blockbuster")
    public ResponseEntity<?> getBlockbusterMoives() {
        ListMovieResponse movies = movieService.getMoviesSortedByIMDBAndDate();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/top-views")
    public ResponseEntity<?> getTopMoviesByViews() {
        ListMovieResponse movies = movieService.getTop10MoviesByViews();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/top-imdb")
    public ResponseEntity<?> getMoviesbyImbdTop() {
        ListMovieResponse movies = movieService.getMoviesbyImbdTop();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getByName(/*@RequestHeader("Authorization") final String authToken,*/ @RequestParam(value = "name", required = true) String nameMovie, @RequestParam(value = "type", required = false) String type) {
        /*if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            return new ResponseEntity<String>("Responding with unauthorized error. Message - {}", HttpStatus.UNAUTHORIZED);
        }*/
        if ("all".equals(type)) {
            List<Optional<MovieDto>> moviesDto = movieService.findMoviesByName(nameMovie);
            FindMoviesReponse response;
            if (!moviesDto.isEmpty()) {
                response = FindMoviesReponse.builder()
                        .data(moviesDto)
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .build();
            } else {
                response = FindMoviesReponse.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Not found Movies")
                        .build();
            }
            return ResponseEntity.ok(response);
        } else {
            MovieDto movieDto = movieService.findByName(nameMovie);
            FindMovieReponse response;
            if (movieDto != null) {
                response = FindMovieReponse.builder()
                        .data(movieDto)
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .build();
            } else {
                response = FindMovieReponse.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message("Not found Movie")
                        .build();
            }
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/favorite-movie")
    public ResponseEntity<?> updateFavoriteMovies(@RequestBody FavoriteMoviesRequest favoriteMoviesRequest) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            // Thực hiện xử lý logic để cập nhật phim ưa thích của người dùng
            boolean updated = favoriteMoviesService.updateFavoriteMovies(favoriteMoviesRequest.getUserId(), favoriteMoviesRequest.getMovieIds());
            if (updated) {
                return ResponseEntity.ok("Favorite movies updated successfully");
            } else {
                return ResponseEntity.ok("Favorite movies updated fail");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating favorite movies");
        }
    }

    @GetMapping("/{movieId}/comments")
    public ResponseEntity<ListMovieCommentResponse> getAllByMovieId(@PathVariable Long movieId) {
        try {
            ListMovieCommentResponse response = movieService.getMovieCommentsById(movieId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ListMovieCommentResponse response = new ListMovieCommentResponse();
            response.setData(null);
//            response.setMessage("Cannot get comments by movie id "+ movieId);
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{movieId}/duration")
    public ResponseEntity<MovieHistoryResponse> getDurationByMovieId(@PathVariable Long movieId, @RequestHeader("Authorization") final String authToken) {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            MovieHistoryResponse response = MovieHistoryResponse.builder()
                    .data(null)
                    .message("Responding with unauthorized error. Message - {}")
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        try {
            MovieHistoryResponse response = movieService.DurationByMovieId(movieId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            MovieHistoryResponse response = new MovieHistoryResponse();
            response.setData(null);
            response.setMessage(e.getMessage());
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping({"/all"})
    public ResponseEntity<PagingMovieResponse> findAllMovies(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "7") int pageSize) {
        PagingMovieResponse response = movieService.findAll(pageNumber, pageSize);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@PathVariable Long id,
                                                     @RequestBody NewMovieRequest updateMovieRequest) {
        try {
            updateMovieRequest.setId(id);
            MovieResponse movieResponse = movieService.update(updateMovieRequest);
            return ResponseEntity.ok(movieResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MovieResponse(null, "Update Movie Failed: " + e.getMessage(), 400));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PagingMovieResponse> deleteMovie(@PathVariable(name = "id") Long movieId, Pageable pageable) {
        PagingMovieResponse response = movieService.deleteMovies(movieId, pageable);
        return ResponseEntity.ok(response);
    }
}
