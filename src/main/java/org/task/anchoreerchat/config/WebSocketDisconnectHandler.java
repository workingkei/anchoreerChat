package org.task.anchoreerchat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.task.anchoreerchat.domain.ChatMessage;
import org.task.anchoreerchat.domain.ChatMessageRepository;
import org.task.anchoreerchat.domain.UserActivity;
import org.task.anchoreerchat.domain.UserActivityRepository;
import org.task.anchoreerchat.dto.ChatMessageRes;
import org.task.anchoreerchat.dto.ChatRoomRes;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class WebSocketDisconnectHandler implements ApplicationListener<SessionDisconnectEvent> {

    private final UserActivityRepository userActivityRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;


    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        UserActivity userActivity = userActivityRepository.findBySessionId(sessionId);
        if (userActivity != null) {
            userActivityRepository.delete(userActivity);
            ChatMessage chatMessage = chatMessageRepository.save(ChatMessage.builder()
                    .chatRoomId(userActivity.getChatRoomId())
                    .sender("System")
                    .content(userActivity.getUserId() + "님이 퇴장하셨습니다.")
                    .build());

            messagingTemplate.convertAndSend("/topic/" + userActivity.getChatRoomId(), new ChatMessageRes(chatMessage));
            long userActivityCount = userActivityRepository.countByChatRoomIdAndLastConnectedAtAfter(userActivity.getChatRoomId(), LocalDateTime.now().minusMinutes(30));
            messagingTemplate.convertAndSend("/topic/userActivityCount", ChatRoomRes.builder()
                    .chatRoomId(Long.parseLong(userActivity.getChatRoomId()))
                    .userActivityCount(userActivityCount)
                    .build());

        }
    }
}
