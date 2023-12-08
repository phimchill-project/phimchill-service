package com.codegym.phimchill.repository;

import com.codegym.phimchill.entity.Chat;
import com.codegym.phimchill.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    //    Chat findByUserCreatedIdAndReplyToUser_Id(long userCreatedId, long replyToUserId);
//    List<Chat> findAllByUsersInChatOrderById(long userId);
}