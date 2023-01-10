package com.summar.summar.repository;

import com.summar.summar.domain.User;
import com.summar.summar.enumeration.SocialType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{


    boolean existsByUserNickname(String nickname);

    /*boolean existsByUserEmail(String userId);*/
    boolean existsByUserNicknameContains(String nickname);


    Optional<User> findByUserEmail(String userEmail);

    boolean existsByUserEmail(String userEmail);

   // boolean existsByUserEmailAndSocialType(String userEmail, SocialType socialType);

    Optional<User> findByUserNickname(String userNickname);

    Page<User> findByUserNicknameContains(String userNickname, Pageable pageable);

    @Query("select u from User u where u.userNickname >=:word and u.userNickname <:word2")
    Page<User> searchWord(String word,String word2,Pageable pageable);
}
