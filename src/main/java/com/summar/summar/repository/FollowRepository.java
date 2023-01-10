package com.summar.summar.repository;

import com.summar.summar.domain.Follow;
import com.summar.summar.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow,Long> {


    Integer countByFollowedUserAndFollowYn(User followedUserNickname,boolean followYn);



    Optional<Follow> findByFollowedUserAndFollowingUserAndFollowYn(User followedUser, User followingUser, boolean followYn);

    Integer countByFollowingUserAndFollowYn(User followingUser, boolean followYn);

    Page<Follow> findByFollowedUserAndFollowYn(User followedUser, boolean followYn, Pageable pageable);

    Page<Follow> findByFollowingUserAndFollowYn(User followingUser, boolean followYn, Pageable pageable);
}
