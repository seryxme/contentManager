package ng.hotsystems.contentManager.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document("Articles")
public class Article {
    @Id
    private String id;
    private String writer;
    private String title;
    private String body;
    private int numberOfLikes;
    @DBRef
    private List<Comment> comments = new ArrayList<>();
}
