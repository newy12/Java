package com.summar.summar.repository;


import com.summar.summar.config.TestConfig;
import com.summar.summar.domain.Follow;
import com.summar.summar.domain.User;
import com.summar.summar.helper.UserHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("h2")
@Import(TestConfig.class)
public class FollowRepositoryTest {

    @Autowired
    FollowRepository followRepository;

    @Autowired
    UserRepository userRepository;


}
