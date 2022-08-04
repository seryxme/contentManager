package ng.hotsystems.contentManager.data.reporsitories;

import ng.hotsystems.contentManager.data.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void saveUserTest() {
        User user = new User();
        var savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());
    }

}