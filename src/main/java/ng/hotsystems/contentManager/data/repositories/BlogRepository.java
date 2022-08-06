package ng.hotsystems.contentManager.data.repositories;

import ng.hotsystems.contentManager.data.models.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogRepository extends MongoRepository<Blog, String> {
    Blog findBlogByName(String blogName);
}
