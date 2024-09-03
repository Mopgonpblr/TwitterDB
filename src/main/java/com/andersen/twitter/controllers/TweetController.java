package com.andersen.twitter.controllers;

import com.andersen.twitter.entities.Tweet;
import com.andersen.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private TweetService service;

    @Autowired
    public TweetController(TweetService service) {
        this.service = service;
    }

    @GetMapping(value = "/get")
    public Tweet getTweet() {
        return service.findTweetById(1);
    }

    @PostMapping(value = "/create")
    public void createTweet() {
        service.createTweet(new Tweet(1, "TwitterAdmin", "Hello, #Twitter!"));
    }

    @PostMapping(value = "/delete")
    public void deleteTweet() {
        service.deleteTweet(service.findTweetById(1));
    }

    @GetMapping(value = "/condition")
    public String getConditionalValue() {
        return service.getConditionalValue();
    }
}