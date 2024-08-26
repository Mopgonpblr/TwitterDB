package app.service;

import app.constants.AutoTweets;
import app.dao.UserDao;
import app.entities.Tweet;
import app.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

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