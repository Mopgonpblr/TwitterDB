package service;

import constants.AutoTweets;
import dao.TweetDao;
import dao.UserDao;
import entities.Tweet;
import entities.User;

import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@Service
@ConditionalOnBean(ContextConfiguration.class)
@Transactional
@ComponentScan("entities")
@EntityScan("entities")
public class UserService {

    private final UserDao userDao;

    public UserService(LocalSessionFactoryBean sessionFactory){
        userDao = new UserDao(sessionFactory.getObject());
    }


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
