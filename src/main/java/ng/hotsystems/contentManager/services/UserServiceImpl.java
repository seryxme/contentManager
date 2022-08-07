package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Article;
import ng.hotsystems.contentManager.data.models.Blog;
import ng.hotsystems.contentManager.data.models.User;
import ng.hotsystems.contentManager.data.repositories.UserRepository;
import ng.hotsystems.contentManager.dtos.requests.*;
import ng.hotsystems.contentManager.dtos.responses.*;
import ng.hotsystems.contentManager.exceptions.BlogExistsException;
import ng.hotsystems.contentManager.exceptions.PasswordIncorrectException;
import ng.hotsystems.contentManager.exceptions.UserDoesNotExistException;
import ng.hotsystems.contentManager.exceptions.UserExistsException;
import ng.hotsystems.contentManager.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        if (savedUser == null) throw new UserDoesNotExistException("User not yet registered.");
        else if (!Objects.equals(savedUser.getPassword(), request.getPassword())) throw new PasswordIncorrectException("This password is incorrect.");

        LoginUserResponse response = new LoginUserResponse();
        response.setMessage(String.format("Logged in successfully. Welcome back, %s!", request.getUsername()));

        return response;
    }

    @Override
    public CreateBlogResponse createBlog(CreateBlogRequest request) {
        User registeredUser = userRepository.findUserByUsername(request.getUsername());
        if (registeredUser.getBlog() != null) throw new BlogExistsException("You cannot have more than one blog.");

        Blog newBlog = blogService.createBlog(request);
        registeredUser.setBlog(newBlog);

        userRepository.save(registeredUser);

        CreateBlogResponse response = new CreateBlogResponse();
        response.setMessage(String.format("Blog with title '%s' has been created. Write your first post.", request.getName()));

        return response;
    }

    @Override
    public List<FindBlogArticlesResponse> viewBlog(String blogName) {

        return blogService.viewBlog(blogName);
    }

    @Override
    public AddArticleResponse addArticle(AddArticleRequest request) {
        blogService.addArticle(request);

        AddArticleResponse response = new AddArticleResponse();
        response.setMessage(String.format("Article with title '%s' has been added to your blog.", request.getTitle()));

        return response;
    }

    @Override
    public DeleteArticleResponse deleteArticle(DeleteArticleRequest deleteRequest) {
        blogService.deleteArticle(deleteRequest);

        DeleteArticleResponse response = new DeleteArticleResponse();
        response.setMessage(String.format("Article with title '%s' has been deleted from your blog.", deleteRequest.getTitle()));

        return response;
    }

    @Override
    public FindArticleResponse viewArticle(FindArticleRequest request) {
        Article foundArticle = blogService.viewArticle(request);
        FindArticleResponse response = new FindArticleResponse();
        Mapper.map(foundArticle, response);
        response.setBlogName(request.getBlogName());
        return response;
    }

    @Override
    public AddCommentResponse addComment(AddCommentRequest request) {
        blogService.addComment(request);

        AddCommentResponse response = new AddCommentResponse();
//        Mapper.map(response, request);
        response.setMessage("Your comment has been sent.");

        return response;
    }
}
