package com.codegym.phimchill.converter;

import com.codegym.phimchill.dto.ChatDto;
import com.codegym.phimchill.entity.Chat;

import java.util.List;

public interface ChatConverter {
    ChatDto convertToDto(Chat chat);

    List<ChatDto> convertToDtoList(List<Chat> chatList);
}
