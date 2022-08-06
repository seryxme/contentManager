package ng.hotsystems.contentManager.controllers;

import ng.hotsystems.contentManager.dtos.requests.*;
import ng.hotsystems.contentManager.dtos.responses.AddCommentResponse;
import org.springframework.http.ResponseEntity;

public interface UserController {
    ResponseEntity<?> registerUser(RegisterUserRequest request);

    ResponseEntity<?> loginUser(LoginUserRequest request);

    ResponseEntity<?> createBlog(CreateBlogRequest request);

    ResponseEntity<?> addArticle(AddArticleRequest request);

    ResponseEntity<?> deleteArticle(DeleteArticleRequest request);

    ResponseEntity<?> viewBlog(String blogName);

    ResponseEntity<?> viewArticle(String blogName, String articleTitle);

    AddCommentResponse addComment(AddCommentRequest request, String blogName, String articleTitle);

}
