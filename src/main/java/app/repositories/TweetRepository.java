package app.repositories;

import app.entities.Tweet;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, String> {
    Tweet findById(int id);

    List<Tweet> findAllByUsername(String username);
}
