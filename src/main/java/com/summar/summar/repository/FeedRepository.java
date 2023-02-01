package com.summar.summar.repository;

import com.summar.summar.domain.Feed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    Page<Feed> findAllByActivatedIsTrueAndSecretYnIsFalseAndTempSaveYnIsFalseAndUserLeaveYnIsFalse(Pageable page);
    Page<Feed> findAllByActivatedIsTrueAndSecretYnIsFalseAndTempSaveYnIsFalseAndUserUserSeq(Long userSeq,Pageable page);
    Page<Feed> findByFeedSeqIn(Pageable page,List<Long> feedSeqIds);
    Feed findOneByFeedSeq(Long feedSeq);
}
