package com.summar.summar.repository;

import com.summar.summar.domain.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting,Long> {
}
