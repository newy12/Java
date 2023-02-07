package com.summar.summar.repository;

import com.summar.summar.domain.FeedLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedLikeRepository extends JpaRepository<FeedLike, Long> {

    Optional<FeedLike> findByFeedFeedSeqAndUserUserSeq(Long feedSeq, Long userSeq);
    boolean existsByActivatedIsTrueAndFeedFeedSeqAndUserUserSeq(Long feedSeq, Long userSeq);
}
