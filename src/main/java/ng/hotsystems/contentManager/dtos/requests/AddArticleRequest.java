package ng.hotsystems.contentManager.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddArticleRequest {
    private String title;
    private String body;
    private String blogName;
    private String username;
}
