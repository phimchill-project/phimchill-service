package com.codegym.phimchill.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "CHAT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name = "USERS_IN_CHAT",
            joinColumns = @JoinColumn(name = "CHAT_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private List<User> usersInChat;
    @OneToMany(mappedBy = "chat")
    private List<Message> messageList;
}
