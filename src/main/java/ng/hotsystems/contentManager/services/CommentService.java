package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Comment;
import ng.hotsystems.contentManager.dtos.requests.AddCommentRequest;

public interface CommentService {

    Comment addComment(AddCommentRequest request);

    void deleteComment(Comment comment);
}
