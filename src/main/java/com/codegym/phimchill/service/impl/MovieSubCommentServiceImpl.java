package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MovieCommentConverter;
import com.codegym.phimchill.converter.MovieSubCommentConverter;
import com.codegym.phimchill.dto.payload.request.LikeMovieCommentRequest;
import com.codegym.phimchill.dto.payload.request.MovieSubCommentRequest;
import com.codegym.phimchill.dto.payload.request.UnLikeMovieCommentRequest;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.dto.payload.response.MovieSubCommentResponse;
import com.codegym.phimchill.dto.payload.response.TotalLikesMovieCommentResponse;
import com.codegym.phimchill.dto.payload.response.TotalLikesMovieSubCommnetResponse;
import com.codegym.phimchill.entity.MovieComment;
import com.codegym.phimchill.entity.MovieSubComment;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.repository.MovieCommentRepository;
import com.codegym.phimchill.repository.MovieSubCommentRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.MovieSubCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieSubCommentServiceImpl implements MovieSubCommentService {
    @Autowired
    private MovieSubCommentRepository movieSubCommentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieCommentRepository movieCommentRepository;
    @Autowired
    private MovieSubCommentConverter movieSubCommentConverter;
    @Autowired
    private MovieCommentConverter movieCommentConverter;

    @Override
    public MovieCommentResponse getSubCommentByCommentId(Long id) throws Exception {
        MovieComment movieComment = movieCommentRepository.findById(id).orElseThrow(
                () -> new Exception("Cannot find movie comment id : " + id)
        );
        return   MovieCommentResponse.builder()
                .data(movieCommentConverter.convertToDto(movieComment))
                .message("find moviecommentId : " + id + " success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public TotalLikesMovieSubCommnetResponse likeMovieSubComment(String email, LikeMovieCommentRequest likeMovieCommentRequest) throws Exception {
        User currentUser = userRepository.findUserByEmail(email);
        if(currentUser == null) {
            throw new Exception("Cannot find user to update increase totalLike subComment");
        }
        MovieSubComment movieSubComment = movieSubCommentRepository.findById(likeMovieCommentRequest.getMovieCommentId()).orElseThrow(
                () -> new Exception("Cannot find movie sub comment id " + likeMovieCommentRequest.getMovieCommentId() + "to update increase totalLike")
        );
        movieSubComment.getUserListLikeSubComment().add(currentUser);
        movieSubComment.setTotalLike(movieSubComment.getTotalLike() + 1);
        MovieSubComment savedMovieSubComment = movieSubCommentRepository.save(movieSubComment);
        currentUser.getMovieSubCommentListUserLiked().add(savedMovieSubComment);
        userRepository.save(currentUser);
        return TotalLikesMovieSubCommnetResponse.builder()
                .data(savedMovieSubComment.getTotalLike())
                .message("Update increase totallike subComment success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public TotalLikesMovieSubCommnetResponse unLikeMovieSubComment(String email, UnLikeMovieCommentRequest unLikeMovieCommentRequest) throws Exception {
        User currentUser = userRepository.findUserByEmail(email);
        if(currentUser == null) {
            throw new Exception("Cannot find user to update decrease totalLike");
        }
        MovieSubComment movieSubComment = movieSubCommentRepository.findById(unLikeMovieCommentRequest.getMovieCommentId()).orElseThrow(
                () -> new Exception("Cannot find movie comment id " + unLikeMovieCommentRequest.getMovieCommentId() + "to update decrease totalLike")
        );
        movieSubComment.getUserListLikeSubComment().remove(currentUser);
        movieSubComment.setTotalLike(movieSubComment.getTotalLike() - 1);
        MovieSubComment savedMovieSubComment = movieSubCommentRepository.save(movieSubComment);
        currentUser.getMovieCommentListUserLiked().remove(savedMovieSubComment);
        userRepository.save(currentUser);
        return TotalLikesMovieSubCommnetResponse.builder()
                .data(savedMovieSubComment.getTotalLike())
                .message("Update decrease totallike success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public MovieSubCommentResponse save(MovieSubCommentRequest request, Long commentID) throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User userComment = userRepository.findUserByEmail(email);
        List<User> listUserTagged = new ArrayList<>();
        if(request.getListUserTagged() != null){
            for(Long id : request.getListUserTagged()){
                User user = userRepository.findById(id).orElse(null);
                if (user != null){
                    listUserTagged.add(user);
                }
            }
        }
        MovieComment movieComment = movieCommentRepository.findById(commentID).orElseThrow(() -> new Exception("Cannot find movie comment in save RepliedMovieCommentResponse"));
        MovieSubComment newComment = MovieSubComment.builder()
                .comment(request.getComment())
                .datePost(request.getDatePost())
                .movieComments(movieComment)
                .subCommentUser(userComment)
                .listUserTaggedInSubComment(listUserTagged)
//                .userListLikeSubComment(new ArrayList<>())
                .build();
        MovieSubComment savedComment = movieSubCommentRepository.save(newComment);
        userComment.getMovieSubCommentsList().add(savedComment);
        userRepository.save(userComment);
        if(!listUserTagged.isEmpty()){
            for(User user : listUserTagged){
                user.getListSubCommentTaggedIn().add(newComment);
                userRepository.save(user);
            }
        }
        return MovieSubCommentResponse.builder()
                .data(movieSubCommentConverter.converToDto(savedComment))
                .message("Save Sub Comment Success")
                .statusCode(201)
                .build();
    }
}

