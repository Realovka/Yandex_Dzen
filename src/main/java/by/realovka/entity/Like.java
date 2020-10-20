package by.realovka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    private long id;
    private long userId;
    private long postId;   //TODO

    public Like(long userId, long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
