package by.realovka.dto.post;

import javax.sql.DataSource;
import java.sql.Date;

public class PostViewOnPageDTO {
    private String title;
    private String text;
    private Date date;

    public PostViewOnPageDTO(String title, String text, Date date) {
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public PostViewOnPageDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PostViewOnPageDTO{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
