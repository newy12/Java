package com.summar.summar.repository;

import com.summar.summar.domain.GatheringNotification;
import com.summar.summar.domain.User;
import com.summar.summar.enumeration.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GatheringNotificationRepository extends JpaRepository<GatheringNotification,Long> {
    List<GatheringNotification> findAllByUserSeqOrderByGatheringNotificationSeqDesc(User user);

    List<GatheringNotification> findAllByNotificationTypeAndUserSeqAndOtherUserSeq(NotificationType notificationType, User followedUser, User followingUser);

}
