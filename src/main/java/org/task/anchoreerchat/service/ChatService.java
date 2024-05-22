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

import java.time.LocalDateTime;
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
        // 채팅방 마지막 메시지 업데이트
        ChatRoom chatRoom = chatRoomRepository.findById(Long.parseLong(chatMessageReq.getChatRoomId()))
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다. id=" + chatMessageReq.getChatRoomId()));
        chatRoom.updateLastMessage(chatMessageReq.getContent(), LocalDateTime.now());
        chatRoomRepository.save(chatRoom);

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
