package service;

import constants.AutoTweets;
import dao.UserDao;
import entities.Tweet;
import entities.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component

public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Value("${property.update-user-and-tweet}")
    private boolean allowed;

    @Transactional
    public User findUser(String username) {
        return userDao.getUser(username);
    }

    @Transactional
    public void createUser(User user) {
        userDao.create(user);
    }

    @Transactional
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Transactional
    public void updateAvatar(String username, String avatar, int tweetId) throws Exception {
        if (allowed) {
            Tweet tweet = new Tweet(tweetId, username, AutoTweets.AVATAR_UPDATED);
            userDao.updateAvatar(username, avatar, tweet);
        } else {
            throw new Exception("property.update-user-and-tweet = false");
        }
    }
}
