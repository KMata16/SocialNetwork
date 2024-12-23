package com.spring.social.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.social.entity.Posts;
import com.spring.social.repository.PostsRepository;

@Service
public class PostsService {

    PostsRepository postsRepository;

    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public Posts persistPost(Posts post) {
        if (!post.getPostText().isBlank() &&
                post.getPostText().length() <= 255) {
            return postsRepository.save(post);
        } else {
            return null;
        }
    }

    public List<Posts> retrieveAllPost() {
        return postsRepository.findAll();
    }

    public Posts retrievePostById(Integer postId) {
        return postsRepository.findByPostId(postId);
    }

    public boolean postExist(Integer postId) {
        return postsRepository.existsByPostId(postId);
    }

    public Integer deletePostById(Integer postId) {
        Posts foundPost = postsRepository.findByPostId(postId);
        if (foundPost != null) {
            postsRepository.delete(foundPost);
            return 1;
        } else {
            return 0;
        }
    }

    public Integer updatePostById(Integer postId, String postText) {
        Posts foundPost = postsRepository.findByPostId(postId);
        if (foundPost != null &&
                !postText.isBlank() &&
                postText.length() >= 1 &&
                postText.length() <= 255) {
            foundPost.setPostText(postText);
            postsRepository.save(foundPost);
            return 1;
        }
        return 0;
    }

    public List<Posts> getAllUserPostById(Integer account_id) {
        List<Posts> postList = postsRepository.findAllByPostedBy(account_id);
        return postList;
    }

}
