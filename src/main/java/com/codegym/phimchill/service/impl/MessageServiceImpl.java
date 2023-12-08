package com.codegym.phimchill.service.impl;

import com.codegym.phimchill.converter.MessageConverter;
import com.codegym.phimchill.dto.payload.request.MessageRequest;
import com.codegym.phimchill.dto.payload.response.ListMessageResponse;
import com.codegym.phimchill.dto.payload.response.MessageResponse;
import com.codegym.phimchill.entity.Chat;
import com.codegym.phimchill.entity.Message;
import com.codegym.phimchill.entity.User;
import com.codegym.phimchill.repository.ChatRepository;
import com.codegym.phimchill.repository.MessageRepository;
import com.codegym.phimchill.repository.UserRepository;
import com.codegym.phimchill.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageConverter messageConverter;

    @Override
    public MessageResponse save(MessageRequest messageRequest, String email) throws Exception {
        User userSend = userRepository.findUserByEmail(email);
        if (userSend == null) {
            throw new Exception("Cannot not find user by email " + email + "in findAllMessageByUserIdRecieved ");
        }
        User userRecieved = userRepository.findUsersById(messageRequest.getReplyToUserId());
        if (userRecieved == null) {
            throw new Exception("Cannot not find user by id " + userSend + "in findAllMessageByUserIdRecieved ");
        }
        List<Chat> chatList = userSend.getChatList();
        Long chatIdTemp = null;
        for (Chat chat : chatList) {
            List<User> usersInChat = chat.getUsersInChat();
            for (User user : usersInChat) {
                if (user.getId() == messageRequest.getReplyToUserId()) {
                    chatIdTemp = chat.getId();
                    break;
                }
            }
            if (chatIdTemp != null) {
                break;
            }
        }
        if (chatIdTemp == null) {
            Chat newChat = new Chat();
            newChat = chatRepository.save(newChat);
            userSend.getChatList().add(newChat);
            userRecieved.getChatList().add(newChat);
            Message message = Message.builder()
                    .content(messageRequest.getContent())
                    .dateSend(messageRequest.getDateSend())
                    .chat(newChat)
                    .build();
            message = messageRepository.save(message);
            return MessageResponse.builder()
                    .id(message.getId())
                    .content(message.getContent())
                    .build();
        }
        Message message = Message.builder()
                .content(messageRequest.getContent())
                .dateSend(messageRequest.getDateSend())
                .chat(Chat.builder().id(chatIdTemp).build())
                .build();
        message = messageRepository.save(message);
        return MessageResponse.builder()
                .id(message.getId())
                .content(message.getContent())
                .build();
    }

    @Override
    public ListMessageResponse findAllMessageByUserIdRecieved(String email, long recipientId) throws Exception {
        User userCurrent = userRepository.findUserByEmail(email);
        if (userCurrent == null) {
            throw new Exception("Cannot not find user by email " + email + "in findAllMessageByUserIdRecieved ");
        }
        User userRecieved = userRepository.findUsersById(recipientId);
        if (userRecieved == null) {
            throw new Exception("Cannot not find user by id " + recipientId + "in findAllMessageByUserIdRecieved ");
        }
        List<Chat> chatList = userCurrent.getChatList();
        Long chatIdTemp = null;
        for (Chat chat : chatList) {
            List<User> usersInChat = chat.getUsersInChat();
            for (User user : usersInChat) {
                if (user.getId() == recipientId) {
                    chatIdTemp = chat.getId();
                    break;
                }
            }
            if (chatIdTemp != null) {
                break;
            }
        }
        List<Message> messageList = messageRepository.findAllByChat_Id(chatIdTemp);
        return ListMessageResponse.builder()
                .data(messageConverter.convertToDtoList(messageList))
                .message("Get all message success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

}
