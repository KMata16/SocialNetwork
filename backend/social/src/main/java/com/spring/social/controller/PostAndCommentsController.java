package com.spring.social.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.social.entity.Comments;
import com.spring.social.entity.Posts;
import com.spring.social.service.CommentsService;
import com.spring.social.service.PostsService;
import com.spring.social.service.UsersService;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
public class PostAndCommentsController {

    PostsService postsService;
    CommentsService commentsService;
    UsersService usersService;

    public PostAndCommentsController(PostsService postsService, CommentsService commentsService,
            UsersService usersService) {
        this.postsService = postsService;
        this.commentsService = commentsService;
        this.usersService = usersService;
    }

    // Posts

    @PostMapping("/posts")
    public @ResponseBody ResponseEntity<Posts> persistPost(@RequestBody Posts post) {
        boolean userExists = usersService.checkUserById(post.getPostedBy());
        Posts newPost = null;
        if (userExists) {
            newPost = postsService.persistPost(post);
        }

        if (newPost == null) {
            return ResponseEntity.status(400).body(null);
        } else {
            return ResponseEntity.ok(newPost);
        }
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Posts>> getAllPosts() {
        List<Posts> postList = postsService.retrieveAllPost();
        if (postList == null) {
            return ResponseEntity.ok().body(null);
        } else {
            return ResponseEntity.ok(postList);
        }
    }

    @GetMapping("/posts/{post_id}")
    public @ResponseBody ResponseEntity<Posts> getPostById(@PathVariable int post_id) {
        Posts retrievedPost = postsService.retrievePostById(post_id);
        if (retrievedPost == null) {
            return ResponseEntity.ok().body(null);
        } else {
            return ResponseEntity.ok(retrievedPost);
        }
    }

    @DeleteMapping("/posts/{post_id}")
    public @ResponseBody ResponseEntity<Integer> deletePost(@PathVariable int post_id) {
        int numDeleted = postsService.deletePostById(post_id);
        if (numDeleted == 1) {
            return ResponseEntity.ok(numDeleted);
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @PatchMapping("/posts/{post_id}")
    public @ResponseBody ResponseEntity<Integer> updatePost(@PathVariable int post_id, @RequestBody Posts post) {
        Integer numUpdated = postsService.updatePostById(post_id, post.getPostText());
        if (numUpdated == 1) {
            return ResponseEntity.ok(numUpdated);
        } else {
            return ResponseEntity.ok(0);
        }
    }

    @GetMapping("/users/{user_id}/posts")
    public @ResponseBody ResponseEntity<List<Posts>> getAllUserPost(@PathVariable int user_id) {
        List<Posts> postList = postsService.getAllUserPostById(user_id);
        return ResponseEntity.ok(postList);
    }

    // Comments

    @PostMapping("/posts/{post_id}/comments")
    public @ResponseBody ResponseEntity<Comments> postComment(@PathVariable int post_id,
            @RequestBody Comments comment) {
        boolean existingPost = postsService.postExist(post_id);
        boolean existingUser = usersService.checkUserById(comment.getPostedByUser());
        Comments newComment = null;
        if (existingPost && existingUser) {
            newComment = commentsService.persistComment(comment);
        }

        if (newComment == null) {
            return ResponseEntity.status(400).body(null);
        } else {
            return ResponseEntity.ok(newComment);
        }
    }

    @GetMapping("/posts/{post_id}/comments")
    public @ResponseBody ResponseEntity<List<Comments>> getAllComments(@PathVariable int post_id) {
        List<Comments> commentList = commentsService.retrieveAllComments(post_id);
        if (commentList == null) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.ok(commentList);
        }
    }

    @GetMapping("/users/{user_id}/comments")
    public @ResponseBody ResponseEntity<List<Comments>> getAllUserComments(@PathVariable int user_id) {
        List<Comments> commentsList = commentsService.retrieveAllUserComments(user_id);
        if (commentsList == null) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.ok(commentsList);
        }
    }

    // check
    @GetMapping("/posts/{post_id}/comments/{comment_id}")
    public @ResponseBody ResponseEntity<Comments> getCommentById(@PathVariable int post_id,
            @PathVariable int comment_id) {
        Comments newComment = commentsService.retrieveCommentById(comment_id);
        if (newComment == null) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.ok(newComment);
        }
    }

    // check
    @PatchMapping("/posts/{post_id}/comments/{comment_id}")
    public @ResponseBody ResponseEntity<Integer> updateComment(@PathVariable int post_id,
            @PathVariable int comment_id, @RequestBody Comments comment) {
        Integer numUpdated = commentsService.updateCommentContent(comment_id, comment.getCommentText());
        if (numUpdated == 1) {
            return ResponseEntity.ok(1);
        } else {
            return ResponseEntity.ok(0);
        }
    }

    @DeleteMapping("/posts/{post_id}/comments/{comment_id}")
    public @ResponseBody ResponseEntity<Integer> deleteComment(@PathVariable int post_id,
            @PathVariable int comment_id) {
        Integer numDeleted = commentsService.deleteComment(comment_id);
        if (numDeleted == 1) {
            return ResponseEntity.ok(1);
        } else {
            return ResponseEntity.ok(0);
        }
    }

}
