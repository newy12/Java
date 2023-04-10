package com.example.insertquerytestinjpa.repository;

import com.example.insertquerytestinjpa.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
