package com.summar.summar.repository;


import com.summar.summar.domain.Major;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MajorRepository extends JpaRepository<Major, Long> {

    List<Major> findAllByParentsSeq(Long parentsSeq);

    List<Major> findAllByParentsSeqIsNull();
}
