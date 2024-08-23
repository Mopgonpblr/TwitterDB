package dao;

import entities.Tweet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TweetDao {
    @Autowired
    private SessionFactory sessionFactory;//  = SessionFactoryProvider.getSessionFactory();

    public TweetDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(Tweet tweet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(tweet);
        transaction.commit();
        session.close();
    }

    public Tweet getTweetById(int id) {
        return sessionFactory.openSession().get(Tweet.class, id);
    }

    public List<Tweet> findTweetsByUsername(String username) {
        return sessionFactory.openSession().createQuery("from Tweet WHERE username = :username", Tweet.class).setParameter("username",username).list();
    }


    public void delete(Tweet tweet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(tweet);
        transaction.commit();
        session.close();
    }
}
