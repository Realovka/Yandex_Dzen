package by.realovka.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostViewOnPageDTO {

    private long id;
    private String title;
    private String text;
    private Timestamp timestamp;
    private String userName;
    private long view;


}
