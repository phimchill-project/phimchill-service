package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.request.MessageRequest;
import com.codegym.phimchill.dto.payload.response.ListMessageResponse;
import com.codegym.phimchill.dto.payload.response.MessageResponse;

public interface MessageService {
    MessageResponse save(MessageRequest messageRequest, String email) throws Exception;

    ListMessageResponse findAllMessageByUserIdRecieved(String email, long recipientId) throws Exception;
}
