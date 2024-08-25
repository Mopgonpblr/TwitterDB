package app.controllers;

import app.entities.Tweet;
import app.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json")
public class TweetController {

    @Autowired
    TweetService service;

    @GetMapping(value = "/")
    public Tweet singleTweet(){
        return service.findTweetsById(1);
    }

}
