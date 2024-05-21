package org.task.anchoreerchat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.task.anchoreerchat.dto.ChatMessageRes;
import org.task.anchoreerchat.service.ChatService;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/chat")
@RestController
public class ChatApiController {

    private final ChatService chatService;

    @GetMapping("/{chatRoomId}/history")
    public List<ChatMessageRes> getChatHistory(@PathVariable String chatRoomId) {
        return chatService.loadChatHistory(chatRoomId);
    }

}
