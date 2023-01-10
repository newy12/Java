package com.summar.summar.repository;

import com.summar.summar.domain.Follow;
import com.summar.summar.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    Page<Follow> findByFollowedUserNicknameAndFollowYn(String userNickname, boolean followYn, Pageable pageable);

    Optional<Follow> findByFollowedUserNicknameAndUserAndFollowYn(String followedUserNickname, User userInfo, boolean followYn);

    Integer countByFollowedUserNicknameAndFollowYn(String userNickname,boolean followYn);

    Integer countByUserAndFollowYn(User user, boolean followYn);

    Page<Follow> findByUserAndFollowYn(User user, boolean followYn,Pageable pageable);
}
