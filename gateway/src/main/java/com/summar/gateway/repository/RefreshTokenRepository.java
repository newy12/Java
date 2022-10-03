package com.summar.gateway.repository;

import com.summar.gateway.domain.RefreshToken;
import com.summar.gateway.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {


}
