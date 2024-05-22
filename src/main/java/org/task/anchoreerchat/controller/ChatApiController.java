package org.task.anchoreerchat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.task.anchoreerchat.dto.ChatMessageRes;
import org.task.anchoreerchat.dto.ChatRoomReq;
import org.task.anchoreerchat.dto.ChatRoomRes;
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

    @GetMapping("/rooms")
    public List<ChatRoomRes> getChatRooms() {
        return chatService.getChatRooms();
    }

    @PostMapping("/rooms")
    public ChatRoomRes createRoom(@RequestBody ChatRoomReq chatRoomReq) {
        return new ChatRoomRes(chatService.createRoom(chatRoomReq.getTitle()));
    }

}
