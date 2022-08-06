package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Article;
import ng.hotsystems.contentManager.data.models.Comment;
import ng.hotsystems.contentManager.dtos.requests.AddArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.AddCommentRequest;
import ng.hotsystems.contentManager.dtos.requests.DeleteArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.FindArticleRequest;

public interface ArticleService {

    Article addArticle(AddArticleRequest request);

    void deleteArticle(DeleteArticleRequest deleteRequest);

    Article viewArticle(FindArticleRequest request);

    Article addComment(AddCommentRequest request);
}
