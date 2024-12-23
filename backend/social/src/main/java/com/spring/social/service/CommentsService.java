package com.spring.social.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.social.entity.Comments;
import com.spring.social.repository.CommentsRepository;

@Service
public class CommentsService {

    CommentsRepository commentsRepository;

    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public Comments persistComment(Comments comment) {
        if (!comment.getCommentText().isBlank() &&
                comment.getCommentText().length() <= 255) {
            return commentsRepository.save(comment);
        } else {
            return null;
        }
    }

    public List<Comments> retrieveAllComments(Integer originalPost) {
        return commentsRepository.findAllByOriginalPost(originalPost);
    }

    public Comments retrieveCommentById(Integer comment_id) {
        return commentsRepository.findByCommentId(comment_id);
    }

    public List<Comments> retrieveAllUserComments(Integer postedByUser) {
        return commentsRepository.findAllByPostedByUser(postedByUser);
    }

    public Integer updateCommentContent(Integer comment_id, String commentText) {
        Comments commentFound = retrieveCommentById(comment_id);
        if (commentFound != null &&
                !commentText.isBlank() &&
                commentText.length() > 1 &&
                commentText.length() <= 255) {
            commentFound.setCommentText(commentText);
            commentsRepository.save(commentFound);
            return 1;
        }
        return 0;
    }

    public Integer deleteComment(Integer comment_id) {
        Comments commentFound = retrieveCommentById(comment_id);
        if (commentFound != null) {
            commentsRepository.delete(commentFound);
            return 1;
        } else {
            return 0;
        }
    }

}
