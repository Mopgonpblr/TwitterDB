package app.controllers;

import app.entities.Tweet;
import app.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TweetController {

    private TweetService service;

    @Autowired
    public TweetController(TweetService service) {
        this.service = service;
    }

    @RequestMapping(value = "/tweet/get",method = RequestMethod.GET)
    public Tweet getTweet() {
        return service.findTweetById(1);
    }

    @RequestMapping(value = "tweet/create", method = RequestMethod.POST)
    public void createTweet() {
        service.createTweet(new Tweet(1, "TwitterAdmin", "Hello, #Twitter!"));
    }

    @RequestMapping(value = "tweet/delete", method = RequestMethod.POST)
    public void deleteTweet() {
        service.deleteTweet(service.findTweetById(1));
    }

    @RequestMapping(value = "/condition", method = RequestMethod.GET)
    public String getConditionalValue() {
        return service.getConditionalValue();
    }
}