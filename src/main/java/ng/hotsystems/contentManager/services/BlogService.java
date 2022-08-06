package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Article;
import ng.hotsystems.contentManager.data.models.Blog;
import ng.hotsystems.contentManager.dtos.requests.AddArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.CreateBlogRequest;

public interface BlogService {

    Blog createBlog(CreateBlogRequest request);

    Article addArticle(AddArticleRequest request);
}
