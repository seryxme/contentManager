package ng.hotsystems.contentManager.data.reporsitories;

import ng.hotsystems.contentManager.data.models.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogRepository extends MongoRepository<Blog, String> {

}
