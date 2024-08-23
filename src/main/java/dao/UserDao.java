package dao;

import entities.Tweet;
import entities.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory; //= SessionFactoryProvider.getSessionFactory();

    public UserDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void create(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public User getUser(String username) {
        return sessionFactory.openSession().get(User.class, username);
    }

    public void updateAvatar(String username, String avatar, Tweet tweet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, username);
        user.setAvatar(avatar);
        session.saveOrUpdate(user);
        session.save(tweet);
        transaction.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }
}
