package by.realovka.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private long id;
    private String title;
    private String description;
    private String text;
    private Timestamp timestamp;
    private long view;
    private long userId;


    public Post(String title, String description, String text, Timestamp timestamp) {
        this.title = title;
        this.description = description;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Post(long id, String title, String text, Timestamp timestamp) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Post(long id, String title, String text, long view, Timestamp timestamp) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.view = view;
        this.timestamp = timestamp;
    }

    public Post(long id, String title, String text, long view, Timestamp timestamp,long userId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.view = view;
        this.timestamp = timestamp;
        this.userId = userId;
    }
}
