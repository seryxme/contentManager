package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.repositories.CommentRepository;
import ng.hotsystems.contentManager.dtos.requests.AddCommentRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceImplTest {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepository commentRepository;

    AddCommentRequest request;

    @BeforeEach
    void setUp() {
        request = new AddCommentRequest();
        request.setCommenterName("umatee");
        request.setArticleTitle("Day of the Edmodo");
        request.setContent("This is quite the story.");
        request.setBlogName("Semicolon Diaries");
        request.setWriter("tee_mix");
    }

    @AfterEach
    void tearDown() {
        commentRepository.deleteAll();
    }

    @Test
    void commentCanBeAddedToArticleTest() {
        assertNotNull(commentService.addComment(request).getId());

        assertEquals(1L, commentRepository.count());
        assertThat(commentRepository.count(), is(1L));
    }

}