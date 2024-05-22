package org.task.anchoreerchat.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chatRoomId;
    private String sessionId; // 웹소켓 세션 ID
    private String userId; // 유저 ID
    private LocalDateTime lastConnectedAt;

    @Builder
    public UserActivity(String chatRoomId, String sessionId, String userId) {
        this.chatRoomId = chatRoomId;
        this.sessionId = sessionId;
        this.userId = userId;
        this.lastConnectedAt = LocalDateTime.now();
    }
}
