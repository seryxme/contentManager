package ng.hotsystems.contentManager.data.repositories;

import ng.hotsystems.contentManager.data.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {

}
