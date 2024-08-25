package app;

import app.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.core.io.Resource;
import app.service.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Main.class, args);
        //new AnnotationConfigApplicationContext(ContextConfiguration.class);

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

        Resource res = context.getResource("classpath:ticketData.txt");
        ArrayList<String> list = createArrayList(res);
        System.out.println(list.get(0));
        System.out.println("\n" + userService.getConditionalValue());

    }


    public static ArrayList<String> createArrayList(Resource res) {
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(res.getContentAsString(Charset.defaultCharset()));
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }

            scanner.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
}