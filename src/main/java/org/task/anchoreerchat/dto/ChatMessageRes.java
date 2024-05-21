package org.task.anchoreerchat.dto;

import lombok.Getter;
import lombok.ToString;
import org.task.anchoreerchat.domain.ChatMessage;
import org.task.anchoreerchat.enums.MessageType;

@ToString
@Getter
public class ChatMessageRes {
    private String chatRoomId;
    private MessageType type;
    private String content;
    private String sender;

    public ChatMessageRes(ChatMessage entity) {
        this.chatRoomId = entity.getChatRoomId();
        this.type = entity.getType();
        this.content = entity.getContent();
        this.sender = entity.getSender();
    }
}
