package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.repositories.ArticleRepository;
import ng.hotsystems.contentManager.data.repositories.BlogRepository;
import ng.hotsystems.contentManager.data.repositories.UserRepository;
import ng.hotsystems.contentManager.dtos.requests.*;
import ng.hotsystems.contentManager.dtos.responses.FindArticleResponse;
import ng.hotsystems.contentManager.dtos.responses.FindBlogArticlesResponse;
import ng.hotsystems.contentManager.exceptions.PasswordIncorrectException;
import ng.hotsystems.contentManager.exceptions.UserDoesNotExistException;
import ng.hotsystems.contentManager.exceptions.UserExistsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ArticleRepository articleRepository;

    private RegisterUserRequest registerUserForm;

    @BeforeEach
    void setUp() {
        registerUserForm = new RegisterUserRequest();
        registerUserForm.setUsername("tee_mix");
        registerUserForm.setEmail("tee_mix@gmail.com");
        registerUserForm.setPassword("allMyLife444");
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        blogRepository.deleteAll();
        articleRepository.deleteAll();
    }

    @Test
    public void registerUser_repositorySizeIsOneTest() {

        userService.registerUser(registerUserForm);

        assertEquals(1L, userRepository.count());
        assertThat(userRepository.count(), is(1L));
    }

    @Test
    public void registerExistingUserThrowsExceptionTest() {

        userService.registerUser(registerUserForm);

        RegisterUserRequest registerUserForm1 = new RegisterUserRequest();
        registerUserForm1.setUsername("tee_mix");
        registerUserForm1.setEmail("tee_mix@gmail.com");
        registerUserForm1.setPassword("allMyLife444");

        assertThrows(UserExistsException.class, () -> userService.registerUser(registerUserForm1));
        assertEquals(1L, userRepository.count());
        assertThat(userRepository.count(), is(1L));
    }

    @Test
    public void registeredUserCanLogInTest() {

        userService.registerUser(registerUserForm);

        LoginUserRequest loginPage = new LoginUserRequest();
        loginPage.setUsername("tee_mix");
        loginPage.setPassword("allMyLife444");

        assertEquals("Logged in successfully. Welcome back, tee_mix!", userService.loginUser(loginPage).getMessage());
        assertThat(userService.loginUser(loginPage).getMessage(), is("Logged in successfully. Welcome back, tee_mix!"));
    }

    @Test
    public void incorrectLoginDetailThrowsExceptionTest() {

        userService.registerUser(registerUserForm);

        LoginUserRequest loginPage = new LoginUserRequest();
        loginPage.setUsername("tee_mi");
        loginPage.setPassword("allMyLife444");

        assertThrows(UserDoesNotExistException.class, () -> userService.loginUser(loginPage));

        LoginUserRequest loginPage1 = new LoginUserRequest();
        loginPage1.setUsername("tee_mix");
        loginPage1.setPassword("allMyLife44");

        assertThrows(PasswordIncorrectException.class, () -> userService.loginUser(loginPage1));
    }

    @Test
    public void userCanCreateABlogTest() {
        userService.registerUser(registerUserForm);

        LoginUserRequest loginPage = new LoginUserRequest();
        loginPage.setUsername("tee_mix");
        loginPage.setPassword("allMyLife444");

        CreateBlogRequest request = new CreateBlogRequest();
        request.setName("Semicolon Diaries");
        request.setUsername("tee_mix");
        userService.createBlog(request);

        assertEquals(1L, blogRepository.count());
        assertThat(blogRepository.count(), is(1L));
    }

    @Test
    public void articlesCanBeAddedToBlogTest() {
        userService.registerUser(registerUserForm);

        LoginUserRequest loginPage = new LoginUserRequest();
        loginPage.setUsername("tee_mix");
        loginPage.setPassword("allMyLife444");

        CreateBlogRequest request = new CreateBlogRequest();
        request.setName("Semicolon Diaries");
        request.setUsername("tee_mix");
        userService.createBlog(request);

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

        userService.addArticle(request1);
        userService.addArticle(request2);

        assertEquals(2L, articleRepository.count());
        assertThat(articleRepository.count(), is(2L));
    }

    @Test
    public void articlesCanBeDeletedFromBlogTest() {
        userService.registerUser(registerUserForm);

        LoginUserRequest loginPage = new LoginUserRequest();
        loginPage.setUsername("tee_mix");
        loginPage.setPassword("allMyLife444");

        CreateBlogRequest request = new CreateBlogRequest();
        request.setName("Semicolon Diaries");
        request.setUsername("tee_mix");
        userService.createBlog(request);

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

        userService.addArticle(request1);
        userService.addArticle(request2);

        DeleteArticleRequest deleteRequest = new DeleteArticleRequest();
        deleteRequest.setTitle("Day of the Edmodo");
        deleteRequest.setBlogName("Semicolon Diaries");
        deleteRequest.setUsername("tee_mix");
        userService.deleteArticle(deleteRequest);

        assertEquals(1L, articleRepository.count());
        assertThat(articleRepository.count(), is(1L));
    }

    @Test
    public void articlesInBlogCanBeViewedTest() {
        userService.registerUser(registerUserForm);

        LoginUserRequest loginPage = new LoginUserRequest();
        loginPage.setUsername("tee_mix");
        loginPage.setPassword("allMyLife444");

        CreateBlogRequest request = new CreateBlogRequest();
        request.setName("Semicolon Diaries");
        request.setUsername("tee_mix");
        userService.createBlog(request);

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

        userService.addArticle(request1);
        userService.addArticle(request2);

        List<FindBlogArticlesResponse> listOfArticles = userService.viewBlog("Semicolon Diaries");

        assertEquals("Day of the Edmodo", listOfArticles.get(0).getTitle());
        assertThat(listOfArticles.get(1).getTitle(), is("It's Fun Friday!"));
    }

    @Test
    public void anArticleInBlogCanBeViewedTest() {
        userService.registerUser(registerUserForm);

        LoginUserRequest loginPage = new LoginUserRequest();
        loginPage.setUsername("tee_mix");
        loginPage.setPassword("allMyLife444");

        CreateBlogRequest request = new CreateBlogRequest();
        request.setName("Semicolon Diaries");
        request.setUsername("tee_mix");
        userService.createBlog(request);

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

        userService.addArticle(request1);
        userService.addArticle(request2);

        FindArticleRequest findRequest = new FindArticleRequest();
        findRequest.setBlogName("Semicolon Diaries");
        findRequest.setUsername("tee_mix");
        findRequest.setTitle("Day of the Edmodo");

        FindArticleResponse response = userService.viewArticle(findRequest);

        assertEquals("It's another day in the Village...", response.getBody());
        assertThat(response.getBody(), is("It's another day in the Village..."));
    }



}