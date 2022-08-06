package ng.hotsystems.contentManager.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindBlogArticlesResponse {
    private String blogName;
    private String title;
    private String bodySummary;
    private int numberOfLikes;
    private int numberOfComments;
}
