package org.task.anchoreerchat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.task.anchoreerchat.domain.ChatMessage;
import org.task.anchoreerchat.domain.ChatMessageRepository;
import org.task.anchoreerchat.domain.ChatRoom;
import org.task.anchoreerchat.domain.ChatRoomRepository;
import org.task.anchoreerchat.dto.ChatMessageReq;
import org.task.anchoreerchat.dto.ChatMessageRes;
import org.task.anchoreerchat.dto.ChatRoomRes;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ChatRoom createRoom(String title) {
        return chatRoomRepository.save(ChatRoom.builder().title(title).build());
    }

    public ChatMessage saveMessage(ChatMessageReq chatMessageReq) {
        return chatMessageRepository.save(chatMessageReq.toEntity());
    }

    public List<ChatMessageRes> loadChatHistory(String chatRoomId) {
        List<ChatMessage> chatMessageList = chatMessageRepository.findAllByChatRoomId(chatRoomId);
        return chatMessageList.stream()
                .map(ChatMessageRes::new)
                .toList();
    }

    public List<ChatRoomRes> getChatRooms() {
        return chatRoomRepository.findAll().stream()
                .map(ChatRoomRes::new)
                .toList();
    }
}
