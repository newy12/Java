package com.summar.summar.repository;

import com.summar.summar.domain.User;
import com.summar.summar.enumeration.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByUserNickname(String nickname);

    /*boolean existsByUserEmail(String userId);*/


    Optional<User> findByUserEmail(String userEmail);

    boolean existsByUserEmail(String userEmail);

    boolean existsByUserEmailAndSocialType(String userEmail, SocialType socialType);

    Optional<User> findByUserNickname(String userNickname);

    List<User> findByUserNicknameContains(String userNickname);

    @Query("select u from User u where u.userNickname >=:word and u.userNickname < :word2")
    List<User> searchWord(String word,String word2);
}
