package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Article;
import ng.hotsystems.contentManager.data.models.Blog;
import ng.hotsystems.contentManager.data.repositories.BlogRepository;
import ng.hotsystems.contentManager.dtos.requests.AddArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.CreateBlogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
