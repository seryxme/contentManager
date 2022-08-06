package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Article;
import ng.hotsystems.contentManager.data.repositories.ArticleRepository;
import ng.hotsystems.contentManager.dtos.requests.AddArticleRequest;
import ng.hotsystems.contentManager.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article addArticle(AddArticleRequest request) {
        Article newArticle = new Article();
        Mapper.map(request, newArticle);

        return articleRepository.save(newArticle);
    }
}
