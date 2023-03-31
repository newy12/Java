package com.example.elsaticsearch.user.domain.search;

import com.example.elsaticsearch.user.domain.Users;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserSearchRepository extends ElasticsearchRepository<Users, Long>, CustomUserSearchRepository {

    List<Users> findByBasicProfile_NameContains(String name);
}
