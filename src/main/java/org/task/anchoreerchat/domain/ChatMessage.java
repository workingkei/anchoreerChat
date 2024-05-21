package org.task.anchoreerchat.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.task.anchoreerchat.enums.MessageType;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatRoomId;
    private String content;
    private String sender;
    private LocalDateTime timestamp;
    private MessageType type;

    @Builder
    public ChatMessage(Long chatRoomId, String content, String sender, LocalDateTime timestamp, MessageType type) {
        this.chatRoomId = chatRoomId;
        this.content = content;
        this.sender = sender;
        this.timestamp = timestamp;
        this.type = type;
    }
}
