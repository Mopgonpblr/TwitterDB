package service;

import constants.AutoTweets;
import dao.UserDao;
import entities.Tweet;
import entities.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@Service
@Transactional
public class UserService {
    private final UserDao userDao = new UserDao();


    public User findUser(String username) {
        return userDao.getUser(username);
    }

    public void createUser(User user) {
        userDao.create(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public void updateAvatar(String username, String avatar, int tweetId) {
        Tweet tweet = new Tweet(tweetId, username, AutoTweets.AVATAR_UPDATED);
        userDao.updateAvatar(username, avatar, tweet);
    }
}
