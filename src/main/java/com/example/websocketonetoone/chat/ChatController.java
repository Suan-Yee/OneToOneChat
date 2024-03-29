package com.example.websocketonetoone.chat;

import com.example.websocketonetoone.user.User;
import com.example.websocketonetoone.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {


    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final UserService userService;

    @MessageMapping("/chat")
    public void processMessage(@Payload UserInfo userInfo){
        log.info("Hello this is me from chatController");
        User sender = userService.findById(userInfo.getSenderId());
        User recipient = userService.findById(userInfo.getRecipientId());

        ChatMessage msg = ChatMessage.builder()
                .sender(sender)
                .recipient(recipient)
                .content(userInfo.getContent())
                .timestamp(userInfo.getTimestamp())
                .build();

        ChatMessage savedMsg = chatMessageService.save(msg);
        messagingTemplate.convertAndSendToUser(
                msg.getRecipient().getId().toString(),"/queue/messages",
                new ChatNotification(
                        savedMsg.getId(),
                        savedMsg.getSender().getId(),
                        savedMsg.getRecipient().getId(),
                        savedMsg.getContent()
                )
        );
    }
    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable Long senderId,
                                                              @PathVariable Long recipientId){
        log.info("Sender id :{} and recipient id {}",senderId,recipientId);
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId,recipientId));
    }
}
