package com.spring.social.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.social.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    boolean existsByUsername(String username);

    boolean existsByAccountId(Integer accountId);

    Users findByUsernameAndPassword(String username, String password);

    Users findByAccountId(Integer accountId);

    Optional<Users> findByUsername(String username);
}
