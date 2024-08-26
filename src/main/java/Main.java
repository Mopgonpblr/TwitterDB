import org.springframework.core.io.Resource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.*;
import service.*;

import static logic.FileParse.createArrayList;


public class Main {
    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        UserService userService = context.getBean("userService", UserService.class);
        TweetService tweetService = context.getBean("tweetService", TweetService.class);
        User user = new User("TwitterAdmin", "Twitter Admin", null);
        Tweet tweet1 = new Tweet(1, "TwitterAdmin", "Hello, #Twitter!");
        Tweet tweet2 = new Tweet(2, "TwitterAdmin", "My second tweet!");

        //userService.createUser(user);

        System.out.println(userService.findUser("TwitterAdmin"));

        //tweetService.createTweet(tweet1);
        //tweetService.createTweet(tweet2);

        System.out.println("=============TWEETS=============");
        List<Tweet> tweets = tweetService.findTweetsByUsername(user.getUsername());
        for (Tweet tweet : tweets) {
            System.out.println(tweet);
        }
/*
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
*/
        //tweetService.deleteTweet(tweet1);

        //userService.deleteUser(user);

        ArrayList<String> list = createArrayList(
                context.getResource("classpath:ticketData.txt"));

        System.out.println(list.get(0));
    }
}