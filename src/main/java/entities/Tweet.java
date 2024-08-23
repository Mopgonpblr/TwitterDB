package entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tweets")
public class Tweet {
    @Id
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "text")
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


    public String toString() {
        return "\t"+ this.username + "\n" + this.text + "\n " + this.creationDate + "\n\n";
    }
}
