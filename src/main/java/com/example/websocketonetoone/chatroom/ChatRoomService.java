package com.example.websocketonetoone.chatroom;

import com.example.websocketonetoone.user.User;
import com.example.websocketonetoone.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public Optional<String> getChatRoomId(Long senderId,
                                          Long recipientId,
                                          boolean createNewRoomIfNotExists){
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId,recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(createNewRoomIfNotExists){
                        var chatId = createChatId(senderId,recipientId);
                            return Optional.of(chatId);
                        }
                        return Optional.empty();
                });
    }
    private String createChatId(Long senderId,Long recipientId){

        User sender = userRepository.findById(senderId).orElse(null);
        User recipient = userRepository.findById(recipientId).orElse(null);

        var chatId = String.format("%s_%s",sender.getNickName(),recipient.getNickName());

        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId).sender(sender).recipient(recipient).build();

        ChatRoom recipientSender = ChatRoom.builder()
                .chatId(chatId).sender(recipient).recipient(sender).build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        return chatId;
    }
}
