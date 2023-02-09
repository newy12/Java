package com.summar.summar.repository;

import com.summar.summar.domain.GatheringNotification;
import com.summar.summar.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GatheringNotificationRepository extends JpaRepository<GatheringNotification,Long> {
    List<GatheringNotification> findAllByUserSeq(User user);
}
