package com.spring.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.social.entity.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer> {

    Posts findByPostId(Integer postId);

    List<Posts> findAllByPostedBy(Integer PostedBy);

    boolean existsByPostId(Integer postId);
}
