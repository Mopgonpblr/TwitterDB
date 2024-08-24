import entities.*;
import org.springframework.core.io.Resource;
import service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class Main {
    public static void main(String[] args) throws IOException {

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

        Resource res = context.getResource("classpath:ticketData.txt");
        ArrayList<String> list = createArrayList(res);
        System.out.println(list.get(0));
    }

    public static ArrayList<String> createArrayList(Resource res) throws IOException {
        ArrayList<String> list = new ArrayList<>();

        Scanner scanner = new Scanner(res.getContentAsString(Charset.defaultCharset()));
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }

        scanner.close();
        return list;
    }
}