package ng.hotsystems.contentManager.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Bloggers")
@NoArgsConstructor
public class User {
    @Id
    private String Id;
    private String username;
    @DBRef
    private Blog blog;
    private String email;
    private String password;
}
