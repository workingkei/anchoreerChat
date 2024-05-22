package org.task.anchoreerchat.dto;

import lombok.Getter;
import lombok.ToString;
import org.task.anchoreerchat.domain.ChatRoom;

import java.time.LocalDateTime;

@ToString
@Getter
public class ChatRoomRes {
    private Long chatRoomId;
    private String title; // 채팅방 제목
    private String lastMessage; // 최근 메시지 내용
    private LocalDateTime lastMessageTime; // 최근 메시지 시각

    public ChatRoomRes(ChatRoom entity) {
        this.chatRoomId = entity.getChatRoomId();
        this.title = entity.getTitle();
        this.lastMessage = entity.getLastMessage();
        this.lastMessageTime = entity.getLastMessageTime();
    }
}
