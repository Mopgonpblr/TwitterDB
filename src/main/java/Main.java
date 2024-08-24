import entities.*;
import service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

//@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        UserService userService = context.getBean("userService", UserService.class);
        TweetService tweetService = context.getBean("tweetService", TweetService.class);

        User user = new User("TwitterAdmin", "Twitter Admin", null);
        Tweet tweet1 = new Tweet(1, "TwitterAdmin", "Hello, #Twitter!");
        Tweet tweet2 = new Tweet(2, "TwitterAdmin", "My second tweet!");

        userService.createUser(user);

        System.out.println(userService.findUser("TwitterAdmin"));

        tweetService.createTweet(tweet1);
        tweetService.createTweet(tweet2);


        System.out.println("=============TWEETS=============");
        List<Tweet> tweets = tweetService.findTweetsByUsername(user.getUsername());
        for (Tweet tweet : tweets) {
            System.out.println(tweet);
        }


        try {
            userService.updateAvatar(user.getUsername(), "Bird.png", 3);
            System.out.println(userService.findUser(user.getUsername()));
            tweets = tweetService.findTweetsByUsername(user.getUsername());
            for (Tweet tweet : tweets) {
                System.out.println(tweet);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        tweetService.deleteTweet(tweet1);

        userService.deleteUser(user);
    }
}