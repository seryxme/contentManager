package ng.hotsystems.contentManager.utils;

import ng.hotsystems.contentManager.data.models.Article;
import ng.hotsystems.contentManager.data.models.Comment;
import ng.hotsystems.contentManager.data.models.User;
import ng.hotsystems.contentManager.dtos.requests.AddArticleRequest;
import ng.hotsystems.contentManager.dtos.requests.AddCommentRequest;
import ng.hotsystems.contentManager.dtos.requests.RegisterUserRequest;
import ng.hotsystems.contentManager.dtos.responses.AddCommentResponse;
import ng.hotsystems.contentManager.dtos.responses.FindArticleResponse;

public class Mapper {
    public static void map(RegisterUserRequest request, User user) {
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
    }

    public static void map(AddArticleRequest request, Article newArticle) {
        newArticle.setTitle(request.getTitle());
        newArticle.setBody(request.getBody());
        newArticle.setWriter(request.getUsername());
    }

    public static void map(Article article, FindArticleResponse response) {
        response.setTitle(article.getTitle());
        response.setBody(article.getBody());
        response.setNumberOfLikes(article.getNumberOfLikes());
        response.setComments(article.getComments());
        response.setWriter(article.getWriter());
    }

    public static void map(AddCommentRequest request, Comment comment) {
        comment.setCommenterName(request.getCommenterName());
        comment.setContent(request.getContent());
    }

    public static void map(AddCommentResponse response, AddCommentRequest request) {
        response.setContent(request.getContent());
        response.setCommenterName(request.getCommenterName());
    }
}
