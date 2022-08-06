package ng.hotsystems.contentManager.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddCommentResponse {

    private String message;
    private String content;
    private String commenterName;
}
