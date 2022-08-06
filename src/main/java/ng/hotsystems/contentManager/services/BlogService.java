package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Article;
import ng.hotsystems.contentManager.data.models.Blog;
import ng.hotsystems.contentManager.dtos.requests.AddArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.CreateBlogRequest;
import ng.hotsystems.contentManager.dtos.requests.DeleteArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.FindArticleRequest;
import ng.hotsystems.contentManager.dtos.responses.FindBlogArticlesResponse;

import java.util.List;

public interface BlogService {

    Blog createBlog(CreateBlogRequest request);

    Article addArticle(AddArticleRequest request);

    void deleteArticle(DeleteArticleRequest deleteRequest);

    List<FindBlogArticlesResponse> viewBlog(String blogName);

    Article viewArticle(FindArticleRequest request);
}
