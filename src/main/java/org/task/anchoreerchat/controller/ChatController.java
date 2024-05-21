package org.task.anchoreerchat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.task.anchoreerchat.dto.ChatMessageReq;
import org.task.anchoreerchat.service.ChatService;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageReq sendMessage(ChatMessageReq chatMessageReq) {
        chatService.saveMessage(chatMessageReq); // 메시지 저장
        return chatMessageReq; // 메시지를 받아 그대로 전송
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessageReq addUser(ChatMessageReq chatMessageReq, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessageReq.getSender());
        return chatMessageReq;
    }
}
