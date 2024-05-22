package org.task.anchoreerchat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.task.anchoreerchat.domain.*;
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
    private final UserActivityRepository userActivityRepository;

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
        List<ChatRoomRes> chatRoomResList = new java.util.ArrayList<>(chatRoomRepository.findAll().stream()
                .map(ChatRoomRes::new)
                .toList());
        List<UserActivity> userActivityList = userActivityRepository.findAllByChatRoomIdIn(chatRoomResList.stream()
                .map(ChatRoomRes::getChatRoomId)
                .toList());

        chatRoomResList.forEach(chatRoomRes -> {
            chatRoomRes.setUserActivityCount(userActivityList.stream()
                    .filter(userActivity -> Long.parseLong(userActivity.getChatRoomId()) == (chatRoomRes.getChatRoomId()))
                    .filter(userActivity -> userActivity.getLastConnectedAt().isAfter(LocalDateTime.now().minusMinutes(30)))
                    .count());
        });

        chatRoomResList.sort((o1, o2) -> (int) (o2.getUserActivityCount() - o1.getUserActivityCount()));
        return chatRoomResList;
    }

    public void saveUserActivity(String chatRoomId, String sessionId, String userId) {
        userActivityRepository.save(UserActivity.builder()
                .chatRoomId(chatRoomId)
                .sessionId(sessionId)
                .userId(userId)
                .build());
    }
}
