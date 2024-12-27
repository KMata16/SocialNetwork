package com.spring.social.controller;

import com.spring.social.entity.Followers;
import com.spring.social.entity.Users;
import com.spring.social.service.CommentsService;
import com.spring.social.service.FollowersService;
import com.spring.social.service.UsersService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
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
    public @ResponseBody ResponseEntity<Users> loginUser(@RequestBody Users user, HttpSession session) {
        Users userAuthenticated = usersService.loginUser(user);
        if (userAuthenticated == null) {
            return ResponseEntity.status(401).body(null);
        } else {
            session.setAttribute("user", userAuthenticated);
            return ResponseEntity.ok(userAuthenticated);
        }
    }

    @GetMapping("/session")
    public @ResponseBody ResponseEntity<Users> getSession(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return null;
        }
    }

    @GetMapping("/invalidate")
    public void invalidateSession(HttpSession session) {
        session.invalidate();
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

    // add profile management

    @PutMapping("/users/{userId}/username")
    public @ResponseBody ResponseEntity<Integer> updateUsername(@PathVariable int userId,
            @RequestBody Users user) {
        Integer updatedUser = usersService.updateUsername(userId, user);
        if (updatedUser == 0) {
            return ResponseEntity.ok(0);
        } else {
            return ResponseEntity.ok(1);
        }
    }

    @PutMapping("/users/{userId}/email")
    public @ResponseBody ResponseEntity<Integer> updateEmail(@PathVariable int userId, @RequestBody Users user) {
        Integer updatedUser = usersService.updateEmail(userId, user);
        if (updatedUser == 0) {
            return ResponseEntity.ok(0);
        } else {
            return ResponseEntity.ok(1);
        }
    }

    @PutMapping("/users/{userId}/password")
    public @ResponseBody ResponseEntity<Integer> updatePaaword(@PathVariable int userId, @RequestBody Users user) {
        Integer updatedUser = usersService.updatePassword(userId, user);
        if (updatedUser == 0) {
            return ResponseEntity.ok(0);
        } else {
            return ResponseEntity.ok(1);
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
