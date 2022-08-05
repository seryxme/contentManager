package ng.hotsystems.contentManager.data.reporsitories;

import ng.hotsystems.contentManager.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);
}
