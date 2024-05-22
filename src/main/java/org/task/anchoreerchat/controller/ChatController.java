package org.task.anchoreerchat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.task.anchoreerchat.dto.ChatMessageReq;
import org.task.anchoreerchat.service.ChatService;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/chat/{chatRoomId}/sendMessage")
    public ChatMessageReq sendMessage(@DestinationVariable String chatRoomId, ChatMessageReq chatMessageReq) {
        chatMessageReq.setChatRoomId(chatRoomId); // 채팅방 주소 설정
        chatService.saveMessage(chatMessageReq); // 메시지 저장

        messagingTemplate.convertAndSend("/topic/" + chatRoomId, chatMessageReq);
        messagingTemplate.convertAndSend("/topic/lastMessage", chatMessageReq);

        return chatMessageReq; // 메시지를 받아 그대로 전송
    }


}
