package ng.hotsystems.contentManager.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateBlogRequest {

    private String name;
    private String username;
}
