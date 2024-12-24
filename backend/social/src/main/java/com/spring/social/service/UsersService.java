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

    // add profile management

    public Integer updateUsername(int accountId, Users user) {
        Users userFound = userRepository.findByAccountId(accountId);
        if (userFound == null) {
            return 0;
        } else {
            userFound.setUsername(user.getUsername());
            userRepository.save(userFound);
            return 1;
        }
    }

    public Integer updateEmail(int user_id, Users user) {
        Users userFound = userRepository.findByAccountId(user_id);
        if (userFound == null) {
            return 0;
        } else {
            userFound.setEmail(user.getEmail());
            userRepository.save(userFound);
            return 1;
        }
    }

    public Integer updatePassword(int user_id, Users user) {
        Users userFound = userRepository.findByAccountId(user_id);
        if (userFound == null) {
            return 0;
        } else {
            userFound.setPassword(user.getPassword());
            userRepository.save(userFound);
            return 1;
        }
    }
}
