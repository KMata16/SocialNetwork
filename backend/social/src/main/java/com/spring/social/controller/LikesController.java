package com.spring.social.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.social.entity.Likes;
import com.spring.social.service.LikesService;
import com.spring.social.service.PostsService;

@RestController
@CrossOrigin("http://localhost:3000/")
public class LikesController {

    LikesService likesService;
    PostsService postsService;

    public LikesController(LikesService likesService, PostsService postsService) {
        this.likesService = likesService;
        this.postsService = postsService;
    }

    @PostMapping("/posts/{post_id}/like")
    public @ResponseBody ResponseEntity<Likes> postLike(@PathVariable int post_id) {
        boolean postExist = postsService.postExist(post_id);
        if (postExist) {
            Likes likedPost = likesService.postLike(post_id);
            return ResponseEntity.ok(likedPost);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/posts/{post_id}/like")
    public @ResponseBody ResponseEntity<Likes> getLike(@PathVariable int post_id) {
        Likes likeFound = likesService.getLikeByPostId(post_id);
        if (likeFound == null) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.ok(likeFound);
        }
    }

}
