package ng.hotsystems.contentManager.controllers;

import ng.hotsystems.contentManager.dtos.requests.AddArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.CreateBlogRequest;
import ng.hotsystems.contentManager.dtos.requests.LoginUserRequest;
import ng.hotsystems.contentManager.dtos.requests.RegisterUserRequest;
import ng.hotsystems.contentManager.dtos.responses.AddArticleResponse;
import ng.hotsystems.contentManager.dtos.responses.CreateBlogResponse;
import org.springframework.http.ResponseEntity;

public interface UserController {
    ResponseEntity<?> registerUser(RegisterUserRequest request);

    ResponseEntity<?> loginUser(LoginUserRequest request);

    CreateBlogResponse createBlog(CreateBlogRequest request);

    AddArticleResponse addArticle(AddArticleRequest request);

}
