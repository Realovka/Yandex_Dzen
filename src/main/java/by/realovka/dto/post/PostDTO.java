package by.realovka.dto.post;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

public class PostDTO {
    @NotEmpty
    @NotBlank
    private String title;

    @NotEmpty
    @NotBlank
    private String description;

    @NotEmpty
    @NotBlank
    private String textNews;
    private Date data;
    private int userId;

    public PostDTO(String title, String description, String textNews, Date data, int userId) {
        this.title = title;
        this.description = description;
        this.textNews = textNews;
        this.data = data;
        this.userId = userId;
    }

    public PostDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTextNews() {
        return textNews;
    }

    public void setTextNews(String textNews) {
        this.textNews = textNews;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", textNews='" + textNews + '\'' +
                ", data=" + data +
                ", userId=" + userId +
                '}';
    }
}
