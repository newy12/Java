package com.example.elsaticsearch.user.domain.search;

import com.example.elsaticsearch.user.domain.Users;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomUserSearchRepository {

    List<Users> searchByName(String name, Pageable pageable);
}
