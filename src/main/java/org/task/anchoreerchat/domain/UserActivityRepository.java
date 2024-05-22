package org.task.anchoreerchat.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    UserActivity findBySessionId(String sessionId);

    List<UserActivity> findAllByChatRoomIdIn(List<Long> chatRoomIds);

    long countByChatRoomIdAndLastConnectedAtAfter(String chatRoomId, LocalDateTime localDateTime);
}
