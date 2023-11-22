package com.codegym.phimchill.converter;

<<<<<<< HEAD
=======
import com.codegym.phimchill.dto.payload.response.UpcomingMoviesResponse;
>>>>>>> d0c1a91d03c7231111e522d0148999f9897f39ad
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.entity.Movie;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public interface MovieConverter {

     List<UpcomingMoviesResponse> convertToUpcomingMoviesResponse(List<Movie> movie);

     MovieDto convertToDTO(Movie movie);
     List<MovieDto> convertToListDTO(List<Movie> movies);

}
