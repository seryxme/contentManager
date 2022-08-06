package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Article;
import ng.hotsystems.contentManager.data.models.Comment;
import ng.hotsystems.contentManager.data.repositories.ArticleRepository;
import ng.hotsystems.contentManager.dtos.requests.AddArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.AddCommentRequest;
import ng.hotsystems.contentManager.dtos.requests.DeleteArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.FindArticleRequest;
import ng.hotsystems.contentManager.exceptions.ArticleDoesNotExistException;
import ng.hotsystems.contentManager.exceptions.ArticleExistsException;
import ng.hotsystems.contentManager.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @Override
    public Article addArticle(AddArticleRequest request) {
        Article foundArticle = articleRepository.findArticleByTitle(request.getTitle());
        if (foundArticle != null) throw new ArticleExistsException("This article already exists. Write a new one?");

        Article newArticle = new Article();
        Mapper.map(request, newArticle);

        return articleRepository.save(newArticle);
    }

    @Override
    public void deleteArticle(DeleteArticleRequest deleteRequest) {
        Article article = articleRepository.findArticleByTitle(deleteRequest.getTitle());
        if (article == null) throw new ArticleDoesNotExistException("This article does not exist.");

        articleRepository.delete(article);
    }

    @Override
    public Article viewArticle(FindArticleRequest request) {
        Article article = articleRepository.findArticleByTitle(request.getTitle());
        if (article == null) throw new ArticleDoesNotExistException("This article does not exist.");

        return articleRepository.findArticleByTitle(request.getTitle());
    }

    @Override
    public Article addComment(AddCommentRequest request) {
        Comment newComment = commentService.addComment(request);
        Article foundArticle = articleRepository.findArticleByTitle(request.getArticleTitle());
        foundArticle.getComments().add(newComment);

        return articleRepository.save(foundArticle);
    }


}
