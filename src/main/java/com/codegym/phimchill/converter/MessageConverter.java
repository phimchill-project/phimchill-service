package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.MessageDto;
import com.codegym.phimchill.entity.Message;

import java.util.List;

public interface MessageConverter {
    MessageDto convertToDto (Message message);

    List<MessageDto> convertToDtoList(List<Message> messageList);
}
