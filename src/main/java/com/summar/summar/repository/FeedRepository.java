package com.summar.summar.repository;

import com.summar.summar.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    List<Feed> findAllByActivatedIsTrueAndSecretYnIsFalseAndTempSaveYnIsFalse();
}
