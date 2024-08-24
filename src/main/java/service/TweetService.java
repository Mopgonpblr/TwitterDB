package service;

import dao.TweetDao;
import entities.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class TweetService {
    @Autowired
    private TweetDao tweetDao;

    @Transactional
    public Tweet findTweetsById(int id){
        return tweetDao.getTweetById(id);
    }

    @Transactional
    public List<Tweet> findTweetsByUsername(String username){
        return tweetDao.findTweetsByUsername(username);
    }

    @Transactional
    public void createTweet(Tweet tweet){
        tweetDao.save(tweet);
    }

    @Transactional
    public void deleteTweet(Tweet tweet){
        tweetDao.delete(tweet);
    }
}