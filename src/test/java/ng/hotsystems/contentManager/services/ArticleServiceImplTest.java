package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Article;
import ng.hotsystems.contentManager.data.repositories.ArticleRepository;
import ng.hotsystems.contentManager.dtos.requests.AddArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.DeleteArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.FindArticleRequest;
import ng.hotsystems.contentManager.exceptions.ArticleExistsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceImplTest {
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleRepository articleRepository;

    AddArticleRequest request1;

    @BeforeEach
    void setUp() {
        request1 = new AddArticleRequest();
        request1.setTitle("Day of the Edmodo");
        request1.setBody("It's another day in the Village...");
        request1.setBlogName("Semicolon Diaries");
        request1.setUsername("tee_mix");
    }

    @AfterEach
    void tearDown() {
        articleRepository.deleteAll();
    }

    @Test
    public void articlesCanBeAddedTest() {

        AddArticleRequest request2 = new AddArticleRequest();
        request2.setTitle("It's Fun Friday!");
        request2.setBody("The last Friday of every month is always one to look forward to...");
        request2.setBlogName("Semicolon Diaries");
        request2.setUsername("tee_mix");

        articleService.addArticle(request1);
        articleService.addArticle(request2);

        assertEquals(2L, articleRepository.count());
        assertThat(articleRepository.count(), is(2L));
    }

    @Test
    public void addingExistingArticleTitleThrowsExceptionTest() {

        articleService.addArticle(request1);

        AddArticleRequest request2 = new AddArticleRequest();
        request2.setTitle("Day of the Edmodo");
        request2.setBody("The last Friday of every month is always one to look forward to...");
        request2.setBlogName("Semicolon Diaries");
        request2.setUsername("tee_mix");

        assertThrows(ArticleExistsException.class, () -> articleService.addArticle(request2));
        assertEquals(1L, articleRepository.count());
        assertThat(articleRepository.count(), is(1L));
    }

    @Test
    public void articlesCanBeDeletedTest() {

        AddArticleRequest request2 = new AddArticleRequest();
        request2.setTitle("It's Fun Friday!");
        request2.setBody("The last Friday of every month is always one to look forward to...");
        request2.setBlogName("Semicolon Diaries");
        request2.setUsername("tee_mix");

        articleService.addArticle(request1);
        articleService.addArticle(request2);

        DeleteArticleRequest deleteRequest = new DeleteArticleRequest();
        deleteRequest.setTitle("Day of the Edmodo");
        deleteRequest.setBlogName("Semicolon Diaries");
        deleteRequest.setUsername("tee_mix");
        articleService.deleteArticle(deleteRequest);

        assertEquals(1L, articleRepository.count());
        assertThat(articleRepository.count(), is(1L));
    }

    @Test
    public void deletingArticleThatDoesNotExistThrowsExceptionTest() {

        articleService.addArticle(request1);

        DeleteArticleRequest deleteRequest = new DeleteArticleRequest();
        deleteRequest.setTitle("It's Fun Friday!");
        deleteRequest.setBlogName("Semicolon Diaries");
        deleteRequest.setUsername("tee_mix");

        assertThrows(ArticleExistsException.class, () -> articleService.deleteArticle(deleteRequest));
        assertEquals(1L, articleRepository.count());
        assertThat(articleRepository.count(), is(1L));
    }

    @Test
    public void anArticleCanBeViewedTest() {

        AddArticleRequest request2 = new AddArticleRequest();
        request2.setTitle("It's Fun Friday!");
        request2.setBody("The last Friday of every month is always one to look forward to...");
        request2.setBlogName("Semicolon Diaries");
        request2.setUsername("tee_mix");

        articleService.addArticle(request1);
        articleService.addArticle(request2);

        FindArticleRequest findRequest = new FindArticleRequest();
        findRequest.setBlogName("Semicolon Diaries");
        findRequest.setUsername("tee_mix");
        findRequest.setTitle("Day of the Edmodo");

        Article foundArticle = articleService.viewArticle(findRequest);

        assertEquals("It's another day in the Village...", foundArticle.getBody());
        assertThat(foundArticle.getBody(), is("It's another day in the Village..."));
    }

    @Test
    public void viewingArticleThatDoesNotExistThrowsExceptionTest() {

        articleService.addArticle(request1);

        FindArticleRequest findRequest = new FindArticleRequest();
        findRequest.setBlogName("Semicolon Diaries");
        findRequest.setUsername("tee_mix");
        findRequest.setTitle("It's Fun Friday!");

        assertThrows(ArticleExistsException.class, () -> articleService.viewArticle(findRequest));
    }

}