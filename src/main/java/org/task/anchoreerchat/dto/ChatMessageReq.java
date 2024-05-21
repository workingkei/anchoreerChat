package org.task.anchoreerchat.dto;

import lombok.Data;
import lombok.Setter;
import org.task.anchoreerchat.domain.ChatMessage;
import org.task.anchoreerchat.enums.MessageType;

import java.time.LocalDateTime;

@Data
public class ChatMessageReq {

    @Setter
    private String chatRoomId;
    private MessageType type;
    private String content;
    private String sender;


    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .chatRoomId(chatRoomId)
                .type(type)
                .content(content)
                .sender(sender)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
