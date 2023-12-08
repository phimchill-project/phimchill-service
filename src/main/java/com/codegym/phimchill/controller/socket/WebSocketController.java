package com.codegym.phimchill.controller.socket;

import com.codegym.phimchill.dto.payload.request.MessageRequest;
import com.codegym.phimchill.dto.payload.response.MessageResponse;
import com.codegym.phimchill.dto.payload.response.MovieCommentResponse;
import com.codegym.phimchill.service.MessageService;
import com.codegym.phimchill.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {
    @Autowired
    private SecurityService securityService;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/message")
    @SendTo("/user/{recievedId}")
    public MessageResponse receiveMessage(@Payload MessageRequest messageRequest, @RequestHeader("Authorization") final String authToken) {
        if (!securityService.isAuthenticated() && !securityService.isValidToken(authToken)) {
            MessageResponse response = MessageResponse.builder()
                    .content(null)
                    .dateSend(null)
                    .build();
            return response;
        }
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            MessageResponse response = messageService.save(messageRequest, email);
            return response;
        } catch (Exception e) {
            return MessageResponse.builder()
                    .content(null)
                    .dateSend(null)
                    .build();
        }
    }
}
