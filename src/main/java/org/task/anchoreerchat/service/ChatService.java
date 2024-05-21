package org.task.anchoreerchat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.task.anchoreerchat.domain.ChatMessage;
import org.task.anchoreerchat.domain.ChatMessageRepository;
import org.task.anchoreerchat.domain.ChatRoom;
import org.task.anchoreerchat.domain.ChatRoomRepository;
import org.task.anchoreerchat.dto.ChatMessageReq;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ChatRoom createRoom(String name) {
        return chatRoomRepository.save(ChatRoom.builder().name(name).build());
    }

    public ChatMessage saveMessage(ChatMessageReq chatMessageReq) {
        return chatMessageRepository.save(chatMessageReq.toEntity());
    }
}
