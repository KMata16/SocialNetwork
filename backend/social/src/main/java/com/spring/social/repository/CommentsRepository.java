package com.spring.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.social.entity.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    List<Comments> findAllByOriginalPost(Integer originalPost);

    Comments findByCommentId(Integer commentId);

    List<Comments> findAllByPostedByUser(Integer postedByUser);

}
