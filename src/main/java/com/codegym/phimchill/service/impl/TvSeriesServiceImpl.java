package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.TvSeriesConverter;
import com.codegym.phimchill.converter.UserConverter;
import com.codegym.phimchill.dto.MovieDto;
import com.codegym.phimchill.dto.TvSeriesDto;
import com.codegym.phimchill.dto.payload.request.NewMovieRequest;
import com.codegym.phimchill.dto.payload.request.MovieNameRequest;
import com.codegym.phimchill.dto.payload.response.CheckMovieNameExistResponse;
import com.codegym.phimchill.dto.payload.response.ListMovieResponse;
import com.codegym.phimchill.dto.payload.response.ListTvSeriesResponse;
import com.codegym.phimchill.dto.payload.response.NewMovieResponse;
import com.codegym.phimchill.entity.Movie;
import com.codegym.phimchill.entity.TVSeries;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.repository.TvSeriesPagingRepository;
import com.codegym.phimchill.repository.TvSeriesRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.NameNormalizationService;
import com.codegym.phimchill.service.TvSeriesService;
import com.codegym.phimchill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TvSeriesServiceImpl implements TvSeriesService {
    @Autowired
    private TvSeriesRepository tvSeriesRepository;

    @Autowired
    private TvSeriesPagingRepository tvSeriesPagingRepository;

    @Autowired
    private TvSeriesConverter tvSeriesConverter;

    @Autowired
    private NameNormalizationService nameNormalizationService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public NewMovieResponse create(NewMovieRequest newTvSeriesRequest) {

        return null;
    }

    @Override
    public CheckMovieNameExistResponse isNotExist(MovieNameRequest tvSeriesNameRequest) {
        Optional<TVSeries> tvSeries = Optional.ofNullable(tvSeriesRepository.findByName(tvSeriesNameRequest.getName()));
        if(tvSeries.isEmpty()){
            return CheckMovieNameExistResponse.builder()
                    .data(true)
                    .statusCode(200)
                    .message("TvSeries is not exist")
                    .build();
        }
        return  CheckMovieNameExistResponse.builder()
                .data(false)
                .statusCode(302 )
                .message("TvSeries is not exist")
                .build();
    }

    @Override
    public List<TvSeriesDto> getTop10ByImdb() {
        return tvSeriesConverter.convertToListDTO(tvSeriesRepository.findFirst10ByOrderByImdbDesc());
    }

    @Override
    public List<TvSeriesDto> getTop10Newest() {
        return tvSeriesConverter.convertToListDTO(tvSeriesRepository.findFirst10ByOrderByDateReleaseDesc());
    }

    @Override
    public List<TvSeriesDto> getTop10FavoriteList(Long user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        if (user==null) {
            return null;
        }else return tvSeriesConverter.convertToListDTO(user.getTvSeriesFavoriteList());
    }

    @Override
    public TvSeriesDto findByName(String nameTvSeries){
        nameTvSeries = nameTvSeries.replaceAll("-", " ");
        List<TVSeries> seriesList = tvSeriesRepository.findAll();
        Optional<TVSeries> series = Optional.empty();
        for (var item : seriesList) {
            String tvSeriesName = nameNormalizationService.normalizeName(item.getName());
            if (tvSeriesName.equalsIgnoreCase(nameTvSeries)) {
                series = Optional.of(item);
            }
        }
        return series.map(value -> tvSeriesConverter.convertToDto(value)).orElse(null);
    }

    @Override
    public List<Optional<TvSeriesDto>> findManyTvSeriesByName(String nameMovie) {
        nameMovie = nameNormalizationService.normalizeName(nameMovie);
        List<TVSeries> tvSeries = tvSeriesRepository.findAll();
        List<TvSeriesDto> tvSeriesDtos = tvSeriesConverter.convertToListDTO(tvSeries);
        List<Optional<TvSeriesDto>> tvSeriesDtosNew = new ArrayList<>();
        for (var item : tvSeriesDtos) {
            if (tvSeriesDtosNew.size() == 10)
                break;
            String movieName = nameNormalizationService.normalizeName(item.getName());
            if (movieName.contains(nameMovie)) {
                tvSeriesDtosNew.add(Optional.of(item));
            }
        }
        return tvSeriesDtosNew;
    }

    @Override
    public ListTvSeriesResponse findTvSeriesByCategoryId(Long id) throws Exception {
        List<TVSeries> tvSeriesList = tvSeriesRepository.findAllByCategoryId(id);
        if(tvSeriesList.isEmpty()){
            throw new Exception("Categoty id : " + id + " is not exist");
        }
        List<TvSeriesDto> tvSeriesDtoList = tvSeriesConverter.convertToListDTO(tvSeriesList);
        return ListTvSeriesResponse.builder()
                .data(tvSeriesDtoList)
                .message("Get movies by category " + id + " success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public String addFavoriteList(Long user_id, Long tvSeries_id) {
        User user = userRepository.findById(user_id).orElse(null);
        TVSeries tvSeries = tvSeriesRepository.findById(tvSeries_id).orElse(null);

        if (user == null || tvSeries == null) {
            return "Fail";
        }

        List<TVSeries> tvSeriesList = user.getTvSeriesFavoriteList();

        if (!tvSeriesList.contains(tvSeries)) {
            tvSeriesList.add(tvSeries);
            user.setTvSeriesFavoriteList(tvSeriesList);
            userRepository.save(user);
            System.out.println(user.getTvSeriesFavoriteList().size());
            return "Success";
        } else {
            return "TV Series is already in the favorite list.";
        }
    }

    @Override
    public List<TvSeriesDto> findAll() {
        return tvSeriesConverter.convertToListDTO(tvSeriesRepository.findAll());
    }

    @Override
    public void deleteTVSeries(Long id) {
        Optional<TVSeries> optionalTvSeries = tvSeriesRepository.findById(id);

        if (optionalTvSeries.isPresent()) {
            TVSeries tvSeries = optionalTvSeries.get();

            tvSeries.setIsDelete(true);

            tvSeriesRepository.save(tvSeries);
        } else {
            throw new NoSuchElementException("TV Series not found with id: " + id);
        }
    }

}
