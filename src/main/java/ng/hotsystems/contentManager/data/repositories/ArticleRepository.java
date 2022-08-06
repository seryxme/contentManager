package ng.hotsystems.contentManager.data.repositories;

import ng.hotsystems.contentManager.data.models.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {

}
