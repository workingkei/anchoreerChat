package org.task.anchoreerchat.dto;

import lombok.*;
import org.task.anchoreerchat.domain.ChatRoom;

import java.time.LocalDateTime;

@NoArgsConstructor
@ToString
@Getter
public class ChatRoomRes {
    private Long chatRoomId;
    private String title; // 채팅방 제목
    private String lastMessage; // 최근 메시지 내용
    private LocalDateTime lastMessageTime; // 최근 메시지 시각
    @Setter
    private long userActivityCount = 0; // 채팅방 사용자 수

    public ChatRoomRes(ChatRoom entity) {
        this.chatRoomId = entity.getChatRoomId();
        this.title = entity.getTitle();
        this.lastMessage = entity.getLastMessage();
        this.lastMessageTime = entity.getLastMessageTime();
    }

    @Builder
    public ChatRoomRes(Long chatRoomId, long userActivityCount) {
        this.chatRoomId = chatRoomId;
        this.userActivityCount = userActivityCount;
    }
}
