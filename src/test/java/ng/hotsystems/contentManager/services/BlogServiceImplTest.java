package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Article;
import ng.hotsystems.contentManager.data.repositories.ArticleRepository;
import ng.hotsystems.contentManager.data.repositories.BlogRepository;
import ng.hotsystems.contentManager.dtos.requests.AddArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.CreateBlogRequest;
import ng.hotsystems.contentManager.dtos.requests.DeleteArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.FindArticleRequest;
import ng.hotsystems.contentManager.dtos.responses.FindArticleResponse;
import ng.hotsystems.contentManager.dtos.responses.FindBlogArticlesResponse;
import ng.hotsystems.contentManager.exceptions.ArticleExistsException;
import ng.hotsystems.contentManager.exceptions.BlogDoesNotExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogServiceImplTest {
    @Autowired
    BlogService blogService;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    ArticleRepository articleRepository;

    CreateBlogRequest request;

    @BeforeEach
    void setUp() {
        request = new CreateBlogRequest();
        request.setName("Semicolon Diaries");
        request.setUsername("tee_mix");
    }

    @AfterEach
    void tearDown() {
        articleRepository.deleteAll();
        blogRepository.deleteAll();
    }


    @Test
    public void userCanCreateABlogTest() {

        blogService.createBlog(request);

        assertEquals(1L, blogRepository.count());
        assertThat(blogRepository.count(), is(1L));
    }

    @Test
    public void articlesCanBeAddedToBlogTest() {

        blogService.createBlog(request);

        AddArticleRequest request1 = new AddArticleRequest();
        request1.setTitle("Day of the Edmodo");
        request1.setBody("It's another day in the Village...");
        request1.setBlogName("Semicolon Diaries");
        request1.setUsername("tee_mix");

        AddArticleRequest request2 = new AddArticleRequest();
        request2.setTitle("It's Fun Friday!");
        request2.setBody("The last Friday of every month is always one to look forward to...");
        request2.setBlogName("Semicolon Diaries");
        request2.setUsername("tee_mix");

        blogService.addArticle(request1);
        blogService.addArticle(request2);

        assertEquals(2L, articleRepository.count());
        assertThat(articleRepository.count(), is(2L));
    }

    @Test
    public void articlesCanBeDeletedFromBlogTest() {

        blogService.createBlog(request);

        AddArticleRequest request1 = new AddArticleRequest();
        request1.setTitle("Day of the Edmodo");
        request1.setBody("It's another day in the Village...");
        request1.setBlogName("Semicolon Diaries");
        request1.setUsername("tee_mix");

        AddArticleRequest request2 = new AddArticleRequest();
        request2.setTitle("It's Fun Friday!");
        request2.setBody("The last Friday of every month is always one to look forward to...");
        request2.setBlogName("Semicolon Diaries");
        request2.setUsername("tee_mix");

        blogService.addArticle(request1);
        blogService.addArticle(request2);

        DeleteArticleRequest deleteRequest = new DeleteArticleRequest();
        deleteRequest.setTitle("Day of the Edmodo");
        deleteRequest.setBlogName("Semicolon Diaries");
        deleteRequest.setUsername("tee_mix");
        blogService.deleteArticle(deleteRequest);

        assertEquals(1L, articleRepository.count());
        assertThat(articleRepository.count(), is(1L));
    }

    @Test
    public void articlesInBlogCanBeViewedTest() {

        blogService.createBlog(request);

        AddArticleRequest request1 = new AddArticleRequest();
        request1.setTitle("Day of the Edmodo");
        request1.setBody("It's another day in the Village...");
        request1.setBlogName("Semicolon Diaries");
        request1.setUsername("tee_mix");

        AddArticleRequest request2 = new AddArticleRequest();
        request2.setTitle("It's Fun Friday!");
        request2.setBody("The last Friday of every month is always one to look forward to...");
        request2.setBlogName("Semicolon Diaries");
        request2.setUsername("tee_mix");

        blogService.addArticle(request1);
        blogService.addArticle(request2);

        List<FindBlogArticlesResponse> listOfArticles = blogService.viewBlog("Semicolon Diaries");

        assertEquals("Day of the Edmodo", listOfArticles.get(0).getTitle());
        assertThat(listOfArticles.get(1).getTitle(), is("It's Fun Friday!"));
    }

    @Test
    public void viewingBlogThatDoesNotExistThrowsExceptionTest() {

        blogService.createBlog(request);

        assertThrows(BlogDoesNotExistException.class, () -> blogService.viewBlog("Semicolon"));
    }

    @Test
    public void anArticleInBlogCanBeViewedTest() {

        blogService.createBlog(request);

        AddArticleRequest request1 = new AddArticleRequest();
        request1.setTitle("Day of the Edmodo");
        request1.setBody("It's another day in the Village...");
        request1.setBlogName("Semicolon Diaries");
        request1.setUsername("tee_mix");

        AddArticleRequest request2 = new AddArticleRequest();
        request2.setTitle("It's Fun Friday!");
        request2.setBody("The last Friday of every month is always one to look forward to...");
        request2.setBlogName("Semicolon Diaries");
        request2.setUsername("tee_mix");

        blogService.addArticle(request1);
        blogService.addArticle(request2);

        FindArticleRequest findRequest = new FindArticleRequest();
        findRequest.setBlogName("Semicolon Diaries");
        findRequest.setUsername("tee_mix");
        findRequest.setTitle("Day of the Edmodo");

        Article foundArticle = blogService.viewArticle(findRequest);

        assertEquals("It's another day in the Village...", foundArticle.getBody());
        assertThat(foundArticle.getBody(), is("It's another day in the Village..."));
    }
}