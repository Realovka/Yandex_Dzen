package by.realovka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private long id;
    private String text;
    private long userId;
    private long postId;
    private Date date;

    public Comment(String text, long userId, long postId) {
        this.text = text;
        this.userId = userId;
        this.postId = postId;
    }

    public Comment(String text) {
        this.text = text;
    }

    public Comment(String text, long userId, long postId, Date date) {
        this.text = text;
        this.userId = userId;
        this.postId = postId;
        this.date = date;
    }
}
