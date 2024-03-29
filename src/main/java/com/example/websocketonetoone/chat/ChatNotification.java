package com.example.websocketonetoone.chat;

import com.example.websocketonetoone.user.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@SuperBuilder
public class ChatNotification {

    private Long id;

    private Long senderId;
    private Long recipientId;

    private String content;
}
