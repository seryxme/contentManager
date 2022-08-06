package ng.hotsystems.contentManager.data.repositories;

import ng.hotsystems.contentManager.data.models.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    public void commentCanBeSavedTest() {
        Comment comment = new Comment();

        Comment newComment = commentRepository.save(comment);

        assertEquals(1, commentRepository.count());
        assertNotNull(newComment.getId());
    }

}