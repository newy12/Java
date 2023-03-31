package com.example.elsaticsearch;

import com.example.elsaticsearch.user.domain.search.UserSearchRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = UserSearchRepository.class))
@SpringBootApplication
public class ElsaticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElsaticsearchApplication.class, args);
    }

}
