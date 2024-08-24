package service;

import dao.TweetDao;
import entities.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.List;

@Component
public class TweetService {
    @Autowired
    private TweetDao tweetDao;

    public Tweet findTweetsById(int id){
        return tweetDao.getTweetById(id);
    }

    public List<Tweet> findTweetsByUsername(String username){
        return tweetDao.findTweetsByUsername(username);
    }

    public void createTweet(Tweet tweet){
        tweetDao.save(tweet);
    }

    public void deleteTweet(Tweet tweet){
        tweetDao.delete(tweet);
    }
}