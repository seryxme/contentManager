package ng.hotsystems.contentManager.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddCommentRequest {
    private String content;
    private String commenterName;
    private String articleTitle;
    private String blogName;
}
