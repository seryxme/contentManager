package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.repositories.ArticleRepository;
import ng.hotsystems.contentManager.data.repositories.BlogRepository;
import ng.hotsystems.contentManager.data.repositories.UserRepository;
import ng.hotsystems.contentManager.dtos.requests.AddArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.CreateBlogRequest;
import ng.hotsystems.contentManager.dtos.requests.LoginUserRequest;
import ng.hotsystems.contentManager.dtos.requests.RegisterUserRequest;
import ng.hotsystems.contentManager.exceptions.PasswordIncorrectException;
import ng.hotsystems.contentManager.exceptions.UserDoesNotExistException;
import ng.hotsystems.contentManager.exceptions.UserExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void createdBlogCanBeRetrievedTest() {
        userService.registerUser(registerUserForm);

        LoginUserRequest loginPage = new LoginUserRequest();
        loginPage.setUsername("tee_mix");
        loginPage.setPassword("allMyLife444");

        CreateBlogRequest request = new CreateBlogRequest();
        request.setName("Semicolon Diaries");
        request.setUsername("tee_mix");
        userService.createBlog(request);



        assertEquals("Semicolon Diaries", userService.viewBlog("tee_mix").getName());
        assertThat(userService.viewBlog("tee_mix").getName(), is("Semicolon Diaries"));
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

        userService.addArticle(request1);

        assertEquals(1L, articleRepository.count());
        assertThat(articleRepository.count(), is(1L));
    }

}