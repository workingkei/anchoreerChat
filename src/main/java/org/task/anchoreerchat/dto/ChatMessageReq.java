package org.task.anchoreerchat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.task.anchoreerchat.domain.ChatMessage;
import org.task.anchoreerchat.enums.MessageType;

import java.time.LocalDateTime;

@Getter
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

    @Builder
    public ChatMessageReq(String chatRoomId, MessageType type, String content, String sender) {
        this.chatRoomId = chatRoomId;
        this.type = type;
        this.content = content;
        this.sender = sender;
    }
}
