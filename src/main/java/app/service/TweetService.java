package app.service;

import app.dao.TweetDao;
import app.entities.Tweet;
import app.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Component
@Service
public class TweetService {
    @Autowired
    private TweetDao tweetDao;

    @Autowired
    private TweetRepository repo;

    @Transactional
    public Tweet findTweetsById(int id) {
        return repo.findById(id);
    }

    @Transactional
    public List<Tweet> findTweetsByUsername(String username) {
        //return tweetDao.findTweetsByUsername(username);
        return repo.findAllByUsername(username);
    }

    @Transactional
    public void createTweet(Tweet tweet) {
        repo.save(tweet);
    }

    @Transactional
    public void deleteTweet(Tweet tweet) {
        repo.delete(tweet);
    }
}