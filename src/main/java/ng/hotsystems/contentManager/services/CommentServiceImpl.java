package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.Comment;
import ng.hotsystems.contentManager.data.repositories.CommentRepository;
import ng.hotsystems.contentManager.dtos.requests.AddCommentRequest;
import ng.hotsystems.contentManager.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment addComment(AddCommentRequest request) {
        Comment comment = new Comment();
        Mapper.map(request, comment);

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
