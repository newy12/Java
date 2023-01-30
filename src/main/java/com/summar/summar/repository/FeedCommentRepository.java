package com.summar.summar.repository;

import com.summar.summar.domain.FeedComment;
import com.summar.summar.domain.FeedLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedCommentRepository extends JpaRepository<FeedComment, Long> {

    Page<FeedComment> findAllByFeedFeedSeq(Long feedSeq, Pageable page);
    List<FeedComment> findAllByFeedFeedSeq(Long feedSeq);
    List<FeedComment> findTop3ByFeedFeedSeq(Long feedSeq);
    FeedComment findOneByFeedCommentSeq(Long feedCommentSeq);
}
