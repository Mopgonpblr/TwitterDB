import dao.*;
import entities.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        UserService userService = context.getBean("userService",UserService.class);
        TweetService tweetService = context.getBean("tweetService",TweetService.class);

        User user = new User("TwitterAdmin", "Twitter Admin",null);
        Tweet tweet1 = new Tweet(1,"TwitterAdmin", "Hello, #Twitter!");
        Tweet tweet2 = new Tweet(2,"TwitterAdmin","My second tweet!");

        userService.createUser(user);

        System.out.println(userService.findUser("TwitterAdmin"));

        tweetService.createTweet(tweet1);
        tweetService.createTweet(tweet2);


        //fetch all tickets with user_id equal 1
        System.out.println("=============TWEETS=============");
        List<Tweet> tweets = tweetService.findTweetsByUsername(user.getUsername());
        for(Tweet tweet: tweets){
            System.out.println(tweet);
        }


        userService.updateAvatar(user.getUsername(),"Bird.png", 3);
        System.out.println(userService.findUser(user.getUsername()));
        tweets = tweetService.findTweetsByUsername(user.getUsername());
        for(Tweet tweet: tweets){
            System.out.println(tweet);
        }

        tweetService.deleteTweet(tweet1);

        userService.deleteUser(user);
    }

}
