package dao;

import entities.Tweet;
import entities.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Value("${property.update-user-and-tweet}")
    private boolean allowed;

    @Transactional
    public void create(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Transactional
    public User getUser(String username) {
        return sessionFactory.openSession().get(User.class, username);
    }

    public void updateAvatar(String username, String avatar, Tweet tweet) throws Exception {
        if (allowed) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, username);
            user.setAvatar(avatar);
            session.saveOrUpdate(user);
            session.save(tweet);
            transaction.commit();
            session.close();
        }
        else {
            throw new Exception("property.update-user-and-tweet = false");
        }
    }

    @Transactional
    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }
}
