package ng.hotsystems.contentManager.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserRequest {

    private String email;
    private String password;
}
