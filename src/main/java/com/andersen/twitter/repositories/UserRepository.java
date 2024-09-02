package com.andersen.twitter.repositories;

import com.andersen.twitter.entities.Tweet;
import com.andersen.twitter.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String id);

    @Modifying
    @Query("update User u set u.avatar = :avatar where u.username < :username")
    void updateAvatar(@Param("username") String username, @Param("avatar") String avatar);
}
