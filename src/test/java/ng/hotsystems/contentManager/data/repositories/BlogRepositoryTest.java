package ng.hotsystems.contentManager.data.repositories;

import ng.hotsystems.contentManager.data.models.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogRepositoryTest {

    @Autowired
    BlogRepository blogRepository;

    @Test
    public void blogPostCanBeSavedTest() {
        Blog blog = new Blog();

        Blog newBlog = blogRepository.save(blog);

        assertEquals(1, blogRepository.count());
        assertNotNull(newBlog.getId());
    }
}