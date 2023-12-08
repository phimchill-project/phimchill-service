package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.ChatConverter;
import com.codegym.phimchill.dto.ChatDto;
import com.codegym.phimchill.dto.UserDto;
import com.codegym.phimchill.entity.Chat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatConverterImpl implements ChatConverter {

    @Override
    public ChatDto convertToDto(Chat chat) {
        if(chat == null){
            return null;
        }
        return ChatDto.builder()
                .id(chat.getId())
//                .userCreated(
//                        UserDto.builder()
//                                .id(chat.getUserCreated().getId())
//                                .name(chat.getUserCreated().getName())
//                                .build()
//                )
//                .replyToUser(
//                        UserDto.builder()
//                                .id(chat.getReplyToUser().getId())
//                                .name(chat.getReplyToUser().getName())
//                                .build()
//                )
                .build();
    }

    @Override
    public List<ChatDto> convertToDtoList(List<Chat> chatList) {
        if (chatList == null){
            return null;
        }
        List<ChatDto> chatDtoList = new ArrayList<>();
        for (Chat chat : chatList){
            chatDtoList.add(convertToDto(chat));
        }
        return chatDtoList;
    }
}
