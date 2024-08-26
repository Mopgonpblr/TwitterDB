package app.controllers;

import app.entities.Tweet;
import app.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TweetController {

    private TweetService service;

    @Autowired
    public TweetController(TweetService service) {
        this.service = service;
    }

    @GetMapping(value = "/tweet/get")
    public Tweet getTweet() {
        return service.findTweetById(1);
    }

    @PostMapping(value = "tweet/create")
    public void createTweet() {
        service.createTweet(new Tweet(1, "TwitterAdmin", "Hello, #Twitter!"));
    }

    @PostMapping(value = "tweet/delete")
    public void deleteTweet() {
        service.deleteTweet(service.findTweetById(1));
    }

    @GetMapping(value = "/condition")
    public String getConditionalValue() {
        return service.getConditionalValue();
    }
}