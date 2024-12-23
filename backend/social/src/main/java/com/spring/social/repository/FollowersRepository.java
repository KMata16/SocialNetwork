package com.spring.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.social.entity.Followers;

@Repository
public interface FollowersRepository extends JpaRepository<Followers, Integer> {

    List<Followers> findAllByPrimaryUser(Integer primaryUser);

    List<Followers> findAllBySecondaryUser(Integer secondaryUser);

    Followers findByRelationNumber(Integer relationNumber);
}
