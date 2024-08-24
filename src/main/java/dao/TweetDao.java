package dao;

import entities.Tweet;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class TweetDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(Tweet tweet) {
        sessionFactory.getCurrentSession().save(tweet);
    }

    public Tweet getTweetById(int id) {
        return sessionFactory.getCurrentSession().get(Tweet.class, id);
    }

    public List<Tweet> findTweetsByUsername(String username) {
        return sessionFactory.getCurrentSession().createQuery("from Tweet WHERE username = :username",
                Tweet.class).setParameter("username", username).list();
    }

    public void delete(Tweet tweet) {
        sessionFactory.getCurrentSession().delete(tweet);
    }
}
