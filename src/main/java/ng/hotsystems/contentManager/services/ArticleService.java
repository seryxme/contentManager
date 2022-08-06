package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Article;
import ng.hotsystems.contentManager.dtos.requests.AddArticleRequest;

public interface ArticleService {

    Article addArticle(AddArticleRequest request);
}
