package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.reporsitories.UserRepository;
import ng.hotsystems.contentManager.dtos.requests.LoginUserRequest;
import ng.hotsystems.contentManager.dtos.requests.RegisterUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void registerUser_repositorySizeIsOneTest() {
        RegisterUserRequest registerUserForm = new RegisterUserRequest();
        registerUserForm.setEmail("tee_mix");
        registerUserForm.setPassword("allMyLife444");

        userService.registerUser(registerUserForm);

        assertEquals(1L, userRepository.count());
        assertThat(userRepository.count(), is(1L));
    }

    @Test
    public void registeredUserCanLogInTest() {
        RegisterUserRequest registerUserForm = new RegisterUserRequest();
        registerUserForm.setEmail("tee_mix@gmail.com");
        registerUserForm.setPassword("allMyLife444");

        userService.registerUser(registerUserForm);

        LoginUserRequest loginPage = new LoginUserRequest();
        loginPage.setEmail("tee_mix@gmail.com");
        loginPage.setPassword("allMyLife444");

        assertEquals("Logged in successfully. Welcome back, tee_mix@gmail.com!", userService.loginUser(loginPage).getMessage());
        assertThat(userService.loginUser(loginPage).getMessage(), is("Logged in successfully. Welcome back, tee_mix@gmail.com!"));
    }

}