package com.spring.social.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "commentsTable")
@ToString
@EqualsAndHashCode
public class Comments {

    @Column(name = "commentId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer commentId;

    @Column(name = "commentText")
    private String commentText;

    @Column(name = "postedByUser")
    private Integer postedByUser;

    @Column(name = "originalPost")
    private Integer originalPost;

    public Comments() {
    }

    public Comments(String commentText, Integer postedByUser, Integer originalPost) {
        this.commentText = commentText;
        this.postedByUser = postedByUser;
        this.originalPost = originalPost;
    }

    public Comments(Integer commentId, String commentText, Integer postedByUser, Integer originalPost) {
        this.commentId = commentId;
        this.commentText = commentText;
        this.postedByUser = postedByUser;
        this.originalPost = originalPost;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Integer getPostedByUser() {
        return postedByUser;
    }

    public void setPostedByUser(Integer postedByUser) {
        this.postedByUser = postedByUser;
    }

    public Integer getOriginalPost() {
        return originalPost;
    }

    public void setOriginalPost(Integer originalPost) {
        this.originalPost = originalPost;
    }

}
