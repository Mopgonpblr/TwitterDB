package com.andersen.twitter.service;

import com.andersen.twitter.entities.Tweet;
import com.andersen.twitter.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TweetService {

    private TweetRepository tweetRepository;

    @Autowired
    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Transactional
    public Tweet findTweetById(int id) {
        return tweetRepository.findById(id);
    }

    @Transactional
    public List<Tweet> findTweetsByUsername(String username) {
        return tweetRepository.findAllByUsername(username);
    }

    @Transactional
    public void createTweet(Tweet tweet) {
        tweetRepository.save(tweet);
    }

    @Transactional
    public void deleteTweet(Tweet tweet) {
        tweetRepository.delete(tweet);
    }

    private String conditionalValue;

    public String getConditionalValue() {
        return conditionalValue;
    }

    @Autowired
    public void setConditionalValue(String conditionalValue) {
        this.conditionalValue = conditionalValue;
    }
}