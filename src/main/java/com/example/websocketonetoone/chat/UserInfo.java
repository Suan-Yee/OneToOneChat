package com.example.websocketonetoone.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserInfo {

    private Long senderId;
    private Long recipientId;
    private String content;
    private Date timestamp;
}
