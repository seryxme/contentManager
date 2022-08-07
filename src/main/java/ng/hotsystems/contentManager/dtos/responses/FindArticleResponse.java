package ng.hotsystems.contentManager.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import ng.hotsystems.contentManager.data.models.Comment;

import java.util.List;
@Data
@NoArgsConstructor
public class FindArticleResponse {
    private String writer;
    private String blogName;
    private String title;
    private String body;
    private int numberOfLikes;
    private List<Comment> comments;

    @Override
    public String toString() {
        return "FindArticleResponse{" +
                "writer='" + writer + '\'' +
                ", blogName='" + blogName + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", numberOfLikes=" + numberOfLikes +
                ", comments=" + comments +
                '}';
    }
}
