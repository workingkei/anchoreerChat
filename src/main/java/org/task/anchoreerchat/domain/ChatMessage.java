package org.task.anchoreerchat.domain;

import jakarta.persistence.*;
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
    private String chatRoomId;
    private String content;
    private String sender;
    private LocalDateTime timestamp;
    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Builder
    public ChatMessage(String chatRoomId, String content, String sender, LocalDateTime timestamp, MessageType type) {
        this.chatRoomId = chatRoomId;
        this.content = content;
        this.sender = sender;
        this.timestamp = timestamp;
        this.type = type;
    }
}
