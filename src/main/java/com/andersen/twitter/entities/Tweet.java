package com.andersen.twitter.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tweets")
public class Tweet {
    @Id
    private int id;

    @Column
    private String username;

    @Column
    private String text;

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private LocalDate creationDate;

    public Tweet() {
    }

    public Tweet(int id, String username, String text) {
        this.id = id;
        this.username = username;
        this.text = text;
        this.creationDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "\t" + this.username + "\n" + this.text + "\n " + this.creationDate + "\n\n";
    }
}
