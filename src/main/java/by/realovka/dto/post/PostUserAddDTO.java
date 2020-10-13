package by.realovka.dto.post;


public class PostUserAddDTO {
    private String title;
    private String description;
    private String text;

    public PostUserAddDTO(String title, String description, String text) {
        this.title = title;
        this.description = description;
        this.text = text;
    }

    public PostUserAddDTO() {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "PostViewOnTheFirstPageDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
