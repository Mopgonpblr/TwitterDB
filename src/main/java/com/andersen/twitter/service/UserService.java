package com.andersen.twitter.service;

import com.andersen.twitter.constants.AutoTweets;
import com.andersen.twitter.entities.Tweet;
import com.andersen.twitter.entities.User;
import com.andersen.twitter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    private TweetService tweetService;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Value("${property.update-user-and-tweet}")
    private boolean allowed;

    @Transactional
    public User findUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public void updateAvatar(String username, String avatar, int tweetId) throws Exception {
        if (allowed) {
            userRepository.updateAvatar(username, avatar);
            tweetService.createTweet(new Tweet(tweetId, username, AutoTweets.AVATAR_UPDATED));
        } else {
            throw new Exception("property.update-user-and-tweet = false");
        }
    }

    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }
}