package ng.hotsystems.contentManager.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeleteArticleRequest {

    private String title;
    private String blogName;
    private String username;
}
