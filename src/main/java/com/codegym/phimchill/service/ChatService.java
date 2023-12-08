package com.codegym.phimchill.service;

import com.codegym.phimchill.dto.payload.response.ListChatResponse;

public interface ChatService {
    ListChatResponse findAll(String email) throws Exception;
}
