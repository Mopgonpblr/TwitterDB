package dao;

import entities.Tweet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


public class TweetDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(Tweet tweet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(tweet);
        transaction.commit();
    }

    public Tweet getTweetById(int id) {
        return sessionFactory.openSession().get(Tweet.class, id);
    }

    public List<Tweet> findTweetsByUsername(String username) {
        return sessionFactory.openSession().createQuery("from Tweet WHERE username = :username",
                Tweet.class).setParameter("username", username).list();
    }

    public void delete(Tweet tweet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(tweet);
        transaction.commit();
        session.close();
    }
}
