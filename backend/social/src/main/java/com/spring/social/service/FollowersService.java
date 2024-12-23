package com.spring.social.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.social.entity.Followers;
import com.spring.social.repository.FollowersRepository;

@Service
public class FollowersService {

    FollowersRepository followersRepository;

    public FollowersService(FollowersRepository followersRepository) {
        this.followersRepository = followersRepository;
    }

    public Followers postFollow(Integer primaryUser, Integer follow) {
        Followers newFollow = new Followers(primaryUser, follow);
        return followersRepository.save(newFollow);
    }

    public List<Followers> getAllFollowing(Integer user_id) {
        return followersRepository.findAllByPrimaryUser(user_id);
    }

    public List<Followers> getAllFollowers(Integer user_id) {
        return followersRepository.findAllBySecondaryUser(user_id);
    }

    public Followers getFollowerByRelId(Integer rel_id) {
        return followersRepository.findByRelationNumber(rel_id);
    }

    public Integer deleteFollow(Integer user_id, Integer follow_id) {
        List<Followers> followingList = getAllFollowing(user_id);
        Integer relNumber = null;
        for (Followers temp : followingList) {
            if (temp.getSecondaryUser() == follow_id) {
                relNumber = temp.getRelationNumber();
            }
        }

        if (relNumber != null) {
            Followers target = getFollowerByRelId(relNumber);
            followersRepository.delete(target);
            return 1;
        } else {
            return 0;
        }
    }

}
