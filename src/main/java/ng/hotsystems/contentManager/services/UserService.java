package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Blog;
import ng.hotsystems.contentManager.dtos.requests.AddArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.CreateBlogRequest;
import ng.hotsystems.contentManager.dtos.requests.LoginUserRequest;
import ng.hotsystems.contentManager.dtos.requests.RegisterUserRequest;
import ng.hotsystems.contentManager.dtos.responses.AddArticleResponse;
import ng.hotsystems.contentManager.dtos.responses.CreateBlogResponse;
import ng.hotsystems.contentManager.dtos.responses.LoginUserResponse;
import ng.hotsystems.contentManager.dtos.responses.RegisterUserResponse;


public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest request);

    LoginUserResponse loginUser(LoginUserRequest request);

    CreateBlogResponse createBlog(CreateBlogRequest request);

    Blog viewBlog(String username);

    AddArticleResponse addArticle(AddArticleRequest request);
}
