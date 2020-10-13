package by.realovka.entity;


import java.sql.Date;
import java.util.List;

public class Post {
    private int id;
    private String title;
    private String description;
    private String textNews;
    private Date createdAt;
    private User user;
    private int view;
    private List<Comment> commentList;
    private List<Like> likeList;

    public Post(int id, String title, String description, String textNews, Date createdAt, User user, int view, List<Comment> commentList, List<Like> likeList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.textNews = textNews;
        this.createdAt = createdAt;
        this.user = user;
        this.view = view;
        this.commentList = commentList;
        this.likeList = likeList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Like> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Like> likeList) {
        this.likeList = likeList;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", textNews='" + textNews + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", view=" + view +
                ", commentList=" + commentList +
                ", likeList=" + likeList +
                '}';
    }
}
