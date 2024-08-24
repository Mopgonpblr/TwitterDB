package service;

import constants.AutoTweets;
import dao.UserDao;
import entities.Tweet;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class UserService {

    @Autowired
    private UserDao userDao;

    public User findUser(String username) {
        return userDao.getUser(username);
    }

    public void createUser(User user) {
        userDao.create(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public void updateAvatar(String username, String avatar, int tweetId) throws Exception {
        Tweet tweet = new Tweet(tweetId, username, AutoTweets.AVATAR_UPDATED);
        userDao.updateAvatar(username, avatar, tweet);
    }
}
