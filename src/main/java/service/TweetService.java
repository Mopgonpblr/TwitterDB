package service;

import dao.TweetDao;
import entities.Tweet;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.*;

import java.util.List;

@Component
@Service
@Transactional
public class TweetService {
    private final TweetDao tweetDao = new TweetDao();


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
