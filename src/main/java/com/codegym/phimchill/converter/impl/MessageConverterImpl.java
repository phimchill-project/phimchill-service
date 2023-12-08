package com.codegym.phimchill.converter.impl;

import com.codegym.phimchill.converter.MessageConverter;
import com.codegym.phimchill.dto.MessageDto;
import com.codegym.phimchill.dto.UserDto;
import com.codegym.phimchill.entity.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageConverterImpl implements MessageConverter {
    @Override
    public MessageDto convertToDto(Message message) {
        if(message == null){
            return null;
        }
        return MessageDto.builder()
                .id(message.getId())
                .content(message.getContent())
                .dateSend(message.getDateSend())
                .userSend(
                        UserDto.builder()
                                .id(message.getUserSend().getId())
                                .name(message.getUserSend().getName())
                                .build()
                )
                .build();
    }

    @Override
    public List<MessageDto> convertToDtoList(List<Message> messageList) {
        List<MessageDto> response = new ArrayList<>();
        for(Message message : messageList){
            response.add(convertToDto(message));
        }
        return response;
    }
}
