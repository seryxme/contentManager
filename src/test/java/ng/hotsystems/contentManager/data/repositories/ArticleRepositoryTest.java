package ng.hotsystems.contentManager.data.repositories;

import ng.hotsystems.contentManager.data.models.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Test
    public void blogPostCanBeSavedTest() {
        Article article = new Article();

        Article newArticle = articleRepository.save(article);

        assertEquals(1, articleRepository.count());
        assertNotNull(newArticle.getId());
    }
}