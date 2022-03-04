package com.minutemate.sera_api.domain.account.repository;

import com.minutemate.sera_api.domain.account.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);

    UserEntity getByEmail(String email);
}
