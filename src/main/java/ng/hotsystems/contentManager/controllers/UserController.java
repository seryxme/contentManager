package ng.hotsystems.contentManager.controllers;

import ng.hotsystems.contentManager.dtos.requests.*;
import ng.hotsystems.contentManager.dtos.responses.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {
    ResponseEntity<?> registerUser(RegisterUserRequest request);

    ResponseEntity<?> loginUser(LoginUserRequest request);

    CreateBlogResponse createBlog(CreateBlogRequest request);

    AddArticleResponse addArticle(AddArticleRequest request);

    DeleteArticleResponse deleteArticle(DeleteArticleRequest request);

    List<FindBlogArticlesResponse> viewBlog(String blogName);

    FindArticleResponse viewArticle(String blogName, String articleTitle);

}
