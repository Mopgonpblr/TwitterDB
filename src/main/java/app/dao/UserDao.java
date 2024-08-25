package app.dao;

import app.entities.Tweet;
import app.entities.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void create(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public User getUser(String username) {
        return sessionFactory.getCurrentSession().get(User.class, username);
    }

    public void updateAvatar(String username, String avatar, Tweet tweet) throws Exception {
        User user = sessionFactory.getCurrentSession().get(User.class, username);
        user.setAvatar(avatar);
        sessionFactory.getCurrentSession().saveOrUpdate(user);
        sessionFactory.getCurrentSession().save(tweet);
    }

    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}