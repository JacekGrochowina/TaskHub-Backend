package com.jg.taskhub.user.infrastructure;

import com.jg.taskhub.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
