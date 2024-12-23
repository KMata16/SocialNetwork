package com.spring.social.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "likesTable")
@ToString
@EqualsAndHashCode
public class Likes {

    @Column(name = "numMessagesLiked")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numMessagesLiked;

    @Column(name = "likedMessage")
    private Integer likedMessage;

    @Column(name = "numberOfLikes")
    private Integer numberOfLikes;

    public Likes() {
    }

    public Likes(Integer likedMessage, Integer numberOfLikes) {
        this.likedMessage = likedMessage;
        this.numberOfLikes = numberOfLikes;
    }

    public Likes(Integer numMessagesLiked, Integer likedMessage, Integer numberOfLikes) {
        this.numMessagesLiked = numMessagesLiked;
        this.likedMessage = likedMessage;
        this.numberOfLikes = numberOfLikes;
    }

    public Integer getNumMessagesLiked() {
        return numMessagesLiked;
    }

    public void setNumMessagesLiked(Integer numMessagesLiked) {
        this.numMessagesLiked = numMessagesLiked;
    }

    public Integer getLikedMessage() {
        return likedMessage;
    }

    public void setLikedMessage(Integer likedMessage) {
        this.likedMessage = likedMessage;
    }

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

}
