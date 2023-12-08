package com.codegym.phimchill.controller;

import com.codegym.phimchill.dto.payload.response.ListChatResponse;
import com.codegym.phimchill.dto.payload.response.ListMessageResponse;
import com.codegym.phimchill.service.MessageService;
import com.codegym.phimchill.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/{recipientId}")
    private ResponseEntity<ListMessageResponse> findAll(@PathVariable (name = "recipientId") long recipientId, @RequestHeader("Authorization") final String authToken){
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            ListMessageResponse response = ListMessageResponse.builder()
                    .data(null)
                    .message("Responding with unauthorized error. Message - {}")
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            ListMessageResponse response = messageService.findAllMessageByUserIdRecieved(email, recipientId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ListMessageResponse response = ListMessageResponse.builder()
                    .data(null)
                    .message(e.getMessage())
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
