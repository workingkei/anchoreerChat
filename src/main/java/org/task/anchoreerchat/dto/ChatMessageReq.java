package org.task.anchoreerchat.dto;

import lombok.Data;
import org.task.anchoreerchat.domain.ChatMessage;
import org.task.anchoreerchat.enums.MessageType;

import java.time.LocalDateTime;

@Data
public class ChatMessageReq {

    private MessageType type;
    private String content;
    private String sender;

    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .type(type)
                .content(content)
                .sender(sender)
                .chatRoomId(1L)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
