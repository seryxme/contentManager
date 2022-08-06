package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Article;
import ng.hotsystems.contentManager.data.models.Blog;
import ng.hotsystems.contentManager.data.repositories.BlogRepository;
import ng.hotsystems.contentManager.dtos.requests.*;
import ng.hotsystems.contentManager.dtos.responses.FindBlogArticlesResponse;
import ng.hotsystems.contentManager.exceptions.BlogDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ArticleService articleService;

    @Override
    public Blog createBlog(CreateBlogRequest request) {
        Blog blog = new Blog();
        blog.setName(request.getName());
        return blogRepository.save(blog);
    }

    @Override
    public Article addArticle(AddArticleRequest request) {
        Article newArticle = articleService.addArticle(request);
        Blog blog = blogRepository.findBlogByName(request.getBlogName());

        blog.getArticles().add(newArticle);

        blogRepository.save(blog);
        return newArticle;
    }

    @Override
    public void deleteArticle(DeleteArticleRequest deleteRequest) {
        Blog foundBlog = blogRepository.findBlogByName(deleteRequest.getBlogName());
        List<Article> articlesInBlog = foundBlog.getArticles();
        for (int i = 0; i < articlesInBlog.size(); i++) {
            if (Objects.equals(articlesInBlog.get(i).getTitle(), deleteRequest.getTitle())) {
                articlesInBlog.remove(i);
                break;
            }
        }

        articleService.deleteArticle(deleteRequest);
    }

    @Override
    public List<FindBlogArticlesResponse> viewBlog(String blogName) {
        Blog foundBlog = blogRepository.findBlogByName(blogName);
        if (foundBlog == null) throw new BlogDoesNotExistException("This blog does not exist. Create it?");

        List<Article> articlesInBlog = foundBlog.getArticles();
        List<FindBlogArticlesResponse> responseList = new ArrayList<>();

        for(Article article: articlesInBlog) {
            FindBlogArticlesResponse response = new FindBlogArticlesResponse();
            response.setBlogName(blogName);
            response.setTitle(article.getTitle());
            response.setBodySummary(article.getBody().substring(0, 30) + "...");
            response.setNumberOfLikes(article.getNumberOfLikes());
            response.setNumberOfComments(article.getComments().size());

            responseList.add(response);
        }

        return responseList;
    }

    @Override
    public Article viewArticle(FindArticleRequest request) {
        return articleService.viewArticle(request);
    }

    @Override
    public Blog addComment(AddCommentRequest request) {
        Article updatedArticle = articleService.addComment(request);
        Blog foundBlog = blogRepository.findBlogByName(request.getBlogName());
        foundBlog.getArticles().add(updatedArticle);

        return blogRepository.save(foundBlog);
    }
}
