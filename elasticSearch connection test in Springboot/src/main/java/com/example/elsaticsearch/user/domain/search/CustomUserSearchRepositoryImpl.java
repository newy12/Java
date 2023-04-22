package com.example.elsaticsearch.user.domain.search;

import com.example.elsaticsearch.user.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class CustomUserSearchRepositoryImpl implements CustomUserSearchRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<Users> searchByName(String name, Pageable pageable) {
        Criteria criteria = Criteria.where("basicProfile.name").contains(name);
        Query query = new CriteriaQuery(criteria).setPageable(pageable);
        SearchHits<Users> search = elasticsearchOperations.search(query, Users.class);
        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
