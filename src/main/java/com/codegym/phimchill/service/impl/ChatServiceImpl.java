package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.ChatConverter;
import com.codegym.phimchill.dto.ChatDto;
import com.codegym.phimchill.dto.payload.response.ListChatResponse;
import com.codegym.phimchill.entity.Chat;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.repository.ChatRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatConverter chatConverter;

    @Override
    public ListChatResponse findAll(String email) throws Exception {
//        User user = userRepository.findUserByEmail(email);
//        if(user == null) {
//            throw new Exception("Cannot not find user by email " + email + "to get all chat");
//        }
//        List<Chat> chatList = chatRepository.findByUserCreatedIdAndReplyToUser_Id(user.getId());
//        List<ChatDto> chatDtoList = new ArrayList<>();
//        if(!chatList.isEmpty()){
//            chatDtoList = chatConverter.convertToDtoList(chatList);
//        }
        return ListChatResponse.builder()
                .data(null)
                .message("Get all list by useremai succes")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
