package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Article;
import ng.hotsystems.contentManager.data.models.Blog;
import ng.hotsystems.contentManager.data.models.User;
import ng.hotsystems.contentManager.data.repositories.UserRepository;
import ng.hotsystems.contentManager.dtos.requests.AddArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.CreateBlogRequest;
import ng.hotsystems.contentManager.dtos.requests.LoginUserRequest;
import ng.hotsystems.contentManager.dtos.requests.RegisterUserRequest;
import ng.hotsystems.contentManager.dtos.responses.AddArticleResponse;
import ng.hotsystems.contentManager.dtos.responses.CreateBlogResponse;
import ng.hotsystems.contentManager.dtos.responses.LoginUserResponse;
import ng.hotsystems.contentManager.dtos.responses.RegisterUserResponse;
import ng.hotsystems.contentManager.exceptions.PasswordIncorrectException;
import ng.hotsystems.contentManager.exceptions.UserDoesNotExistException;
import ng.hotsystems.contentManager.exceptions.UserExistsException;
import ng.hotsystems.contentManager.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    BlogService blogService;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        User savedUser = userRepository.findUserByUsername(request.getUsername());
        if (savedUser != null) throw new UserExistsException("This email is already registered.");

        User user = new User();
        Mapper.map(request, user);

        userRepository.save(user);

        RegisterUserResponse response = new RegisterUserResponse();
        response.setMessage(String.format("%s successfully registered.", request.getEmail()));
        return response;
    }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest request) {
        User savedUser = userRepository.findUserByUsername(request.getUsername());
        if (savedUser == null) throw new UserDoesNotExistException("Email not yet registered.");
        else if (!Objects.equals(savedUser.getPassword(), request.getPassword())) throw new PasswordIncorrectException("This password is incorrect.");

        LoginUserResponse response = new LoginUserResponse();
        response.setMessage(String.format("Logged in successfully. Welcome back, %s!", request.getUsername()));

        return response;
    }

    @Override
    public CreateBlogResponse createBlog(CreateBlogRequest request) {
        Blog newBlog = blogService.createBlog(request);

        User registeredUser = userRepository.findUserByUsername(request.getUsername());
        registeredUser.setBlog(newBlog);

        userRepository.save(registeredUser);

        CreateBlogResponse response = new CreateBlogResponse();
        response.setMessage(String.format("Blog with title '%s' has been created. Write your first post.", request.getName()));

        return response;
    }

    @Override
    public Blog viewBlog(String username) {
        return userRepository.findUserByUsername(username).getBlog();
    }

    @Override
    public AddArticleResponse addArticle(AddArticleRequest request) {
        Article newArticle = blogService.addArticle(request);

        User registeredUser = userRepository.findUserByUsername(request.getUsername());
        registeredUser.getBlog().getArticles().add(newArticle);

        userRepository.save(registeredUser);

        AddArticleResponse response = new AddArticleResponse();
        response.setMessage(String.format("Article with title '%s' has been added your blog.", request.getTitle()));

        return response;
    }
}
