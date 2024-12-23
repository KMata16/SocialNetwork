package com.spring.social.service;

import org.springframework.stereotype.Service;

import com.spring.social.entity.Users;
import com.spring.social.repository.UsersRepository;

@Service
public class UsersService {

    UsersRepository userRepository;

    public UsersService(UsersRepository usersRepository) {
        this.userRepository = usersRepository;
    }

    public boolean userExists(Users user) {
        String username = user.getUsername();
        return userRepository.existsByUsername(username);
    }

    public boolean checkUserById(Integer accountId) {
        return userRepository.existsByAccountId(accountId);
    }

    public Users persistUsers(Users user) {
        if (!user.getUsername().isBlank() &&
                user.getPassword().length() >= 4) {
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public Users loginUser(Users user) {
        if (userExists(user)) {
            return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        } else {
            return null;
        }
    }

    public Users getUsername(Integer accountId) {
        Users foundUser = userRepository.findByAccountId(accountId);
        if (foundUser == null) {
            return null;
        } else {
            return foundUser;
        }
    }
}
