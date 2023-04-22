package com.example.elsaticsearch.user.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users,Long> {
    Page<Users> findByBasicProfile_NameContains(String name, Pageable pageable);
}
