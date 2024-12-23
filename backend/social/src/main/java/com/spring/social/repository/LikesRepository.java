package com.spring.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.social.entity.Likes;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {

    Likes findByLikedMessage(Integer likedMessage);

}
