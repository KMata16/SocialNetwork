package com.spring.social.controller;

import com.spring.social.entity.Followers;
import com.spring.social.entity.Users;
import com.spring.social.service.CommentsService;
import com.spring.social.service.FollowersService;
import com.spring.social.service.UsersService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin("http://localhost:3000/")
public class SocialMediaController {

    UsersService usersService;
    CommentsService commentsService;
    FollowersService followersService;

    public SocialMediaController(UsersService usersService, FollowersService followersService) {
        this.usersService = usersService;
        this.followersService = followersService;
    }

    // Users

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<Users> postUser(@RequestBody Users user) {
        boolean userExists = usersService.userExists(user);
        if (!userExists) {
            return ResponseEntity.ok(usersService.persistUsers(user));
        } else {
            return ResponseEntity.status(409).body(null);
        }
    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<Users> loginUser(@RequestBody Users user) {
        Users userAuthenticated = usersService.loginUser(user);
        if (userAuthenticated == null) {
            return ResponseEntity.status(401).body(null);
        } else {
            return ResponseEntity.ok(userAuthenticated);
        }
    }

    @GetMapping("/users/{user_id}")
    public @ResponseBody ResponseEntity<String> getUser(@PathVariable int user_id) {
        Users user = usersService.getUsername(user_id);
        if (user == null) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.ok(user.getUsername());
        }
    }

    // Followers

    @PostMapping("/users/{user_id}/{follow_id}")
    public @ResponseBody ResponseEntity<Followers> addFollower(@PathVariable int user_id,
            @PathVariable int follow_id) {
        boolean userExist = usersService.checkUserById(user_id);
        boolean followExist = usersService.checkUserById(follow_id);
        Followers follow = null;
        if (userExist && followExist) {
            follow = followersService.postFollow(user_id, follow_id);
        }

        if (follow != null) {
            return ResponseEntity.ok(follow);
        } else {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/users/{user_id}/following")
    public @ResponseBody ResponseEntity<List<Followers>> getAllFollowing(@PathVariable int user_id) {
        List<Followers> followingList = followersService.getAllFollowing(user_id);
        if (followingList == null) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.ok(followingList);
        }
    }

    @GetMapping("/users/{user_id}/followers")
    public @ResponseBody ResponseEntity<List<Followers>> getAllFollowers(@PathVariable int user_id) {
        List<Followers> followersList = followersService.getAllFollowers(user_id);
        if (followersList == null) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.ok(followersList);
        }
    }

    @DeleteMapping("/users/{user_id}/{follow_id}")
    public @ResponseBody ResponseEntity<Integer> deleteFollower(@PathVariable int user_id,
            @PathVariable int follow_id) {
        Integer numDeleted = followersService.deleteFollow(user_id, follow_id);
        if (numDeleted == 1) {
            return ResponseEntity.ok(1);
        } else {
            return ResponseEntity.ok(0);
        }
    }

}
