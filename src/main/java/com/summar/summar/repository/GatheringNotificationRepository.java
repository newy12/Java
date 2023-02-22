package com.summar.summar.repository;

import com.summar.summar.domain.GatheringNotification;
import com.summar.summar.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GatheringNotificationRepository extends JpaRepository<GatheringNotification,Long> {
    List<GatheringNotification> findAllByUserSeqOrderByGatheringNotificationSeqDesc(User user);

    List<GatheringNotification> findAllByContent(String body);

    List<GatheringNotification> findAllByContentAndUserSeqAndOtherUserSeq(String body, User followedUser, User followingUser);

    List<GatheringNotification> findAllByContentContains(String userNickname);
}
