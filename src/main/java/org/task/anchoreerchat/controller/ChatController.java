package org.task.anchoreerchat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.task.anchoreerchat.domain.ChatMessage;
import org.task.anchoreerchat.domain.UserActivityRepository;
import org.task.anchoreerchat.dto.ChatMessageReq;
import org.task.anchoreerchat.dto.ChatMessageRes;
import org.task.anchoreerchat.dto.ChatRoomRes;
import org.task.anchoreerchat.enums.MessageType;
import org.task.anchoreerchat.service.ChatService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;
    private final UserActivityRepository userActivityRepository;


    @MessageMapping("/chat/{chatRoomId}/sendMessage")
    public ChatMessageReq sendMessage(@DestinationVariable String chatRoomId, ChatMessageReq chatMessageReq) {
        chatMessageReq.setChatRoomId(chatRoomId); // 채팅방 주소 설정
        chatService.saveMessage(chatMessageReq); // 메시지 저장

        messagingTemplate.convertAndSend("/topic/" + chatRoomId, chatMessageReq);
        messagingTemplate.convertAndSend("/topic/lastMessage", chatMessageReq);

        return chatMessageReq; // 메시지를 받아 그대로 전송
    }

    @MessageMapping("/chat/{chatRoomId}/addUser")
    public void addUser(@DestinationVariable String chatRoomId
            , ChatMessageReq chatMessageReq, SimpMessageHeaderAccessor headerAccessor) {
        ChatMessage chatMessage = chatService.saveMessage(ChatMessageReq.builder()
                .chatRoomId(chatRoomId)
                .sender("System")
                .content(chatMessageReq.getSender() + "님이 입장하셨습니다.")
                .type(MessageType.JOIN)
                .build()); // 메시지 저장
        String sessionId = headerAccessor.getSessionId();
        chatService.saveUserActivity(chatRoomId, sessionId, chatMessageReq.getSender()); // 유저 활동 저장

        messagingTemplate.convertAndSend("/topic/" + chatRoomId, new ChatMessageRes(chatMessage));

        long userActivityCount = userActivityRepository.countByChatRoomIdAndLastConnectedAtAfter(chatRoomId, LocalDateTime.now().minusMinutes(30));
        messagingTemplate.convertAndSend("/topic/userActivityCount", ChatRoomRes.builder()
                .chatRoomId(Long.parseLong(chatRoomId))
                .userActivityCount(userActivityCount)
                .build());
    }

}
