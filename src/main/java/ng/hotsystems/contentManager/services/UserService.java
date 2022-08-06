package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Comment;
import ng.hotsystems.contentManager.dtos.requests.*;
import ng.hotsystems.contentManager.dtos.responses.*;

import java.util.List;


public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest request);

    LoginUserResponse loginUser(LoginUserRequest request);

    CreateBlogResponse createBlog(CreateBlogRequest request);

    List<FindBlogArticlesResponse> viewBlog(String blogName);

    AddArticleResponse addArticle(AddArticleRequest request);

    DeleteArticleResponse deleteArticle(DeleteArticleRequest deleteRequest);

    FindArticleResponse viewArticle(FindArticleRequest request);

    AddCommentResponse addComment(AddCommentRequest request);
}
