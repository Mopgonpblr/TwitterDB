package com.andersen.twitter.dao;

import com.andersen.twitter.entities.Tweet;
import com.andersen.twitter.entities.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void create(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public User getUser(String username) {
        return sessionFactory.getCurrentSession().get(User.class, username);
    }

    public void updateAvatar(String username, String avatar, Tweet tweet){
        User user = sessionFactory.getCurrentSession().get(User.class, username);
        user.setAvatar(avatar);
        sessionFactory.getCurrentSession().saveOrUpdate(user);
        sessionFactory.getCurrentSession().save(tweet);
    }

    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}