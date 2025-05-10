package com.webrise.assignment.repository;

import com.webrise.assignment.domain.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @EntityGraph(attributePaths = {"subscriptions"})
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findUserWithSubscriptions(@Param("id") UUID id);
    @Transactional(readOnly = true)
    boolean existsByEmail(String email);
    @Transactional(readOnly = true)
    boolean existsByEmailAndIdNot(String email, UUID userId);
}
