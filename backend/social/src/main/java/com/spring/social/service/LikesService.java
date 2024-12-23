package com.spring.social.service;

import org.springframework.stereotype.Service;

import com.spring.social.entity.Likes;
import com.spring.social.repository.LikesRepository;

@Service
public class LikesService {

    LikesRepository likesRepository;

    public LikesService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    public Likes getLikeByPostId(Integer post_id) {
        return likesRepository.findByLikedMessage(post_id);
    }

    public Likes postLike(Integer post_id) {
        Likes alreadyLiked = getLikeByPostId(post_id);
        if (alreadyLiked != null) {
            Integer numOfLikes = alreadyLiked.getNumberOfLikes() + 1;
            alreadyLiked.setNumberOfLikes(numOfLikes);
            return likesRepository.save(alreadyLiked);
        } else {
            Likes like = new Likes(post_id, 1);
            return likesRepository.save(like);
        }

    }
}
