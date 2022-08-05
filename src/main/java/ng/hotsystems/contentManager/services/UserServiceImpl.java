package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.User;
import ng.hotsystems.contentManager.data.reporsitories.UserRepository;
import ng.hotsystems.contentManager.dtos.requests.LoginUserRequest;
import ng.hotsystems.contentManager.dtos.requests.RegisterUserRequest;
import ng.hotsystems.contentManager.dtos.responses.LoginUserResponse;
import ng.hotsystems.contentManager.dtos.responses.RegisterUserResponse;
import ng.hotsystems.contentManager.exceptions.PasswordIncorrectException;
import ng.hotsystems.contentManager.exceptions.UserDoesNotExistsException;
import ng.hotsystems.contentManager.exceptions.UserExistsException;
import ng.hotsystems.contentManager.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        User savedUser = userRepository.findUserByUsername(request.getEmail());
        if (savedUser != null) throw new UserExistsException("This email is already registered.");

        User user = new User();
        Mapper.map(request, user);

        userRepository.save(user);

        RegisterUserResponse response = new RegisterUserResponse();
        response.setMessage(String.format("%s successfully registered.", request.getEmail()));
        return response;
    }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest request) {
        User savedUser = userRepository.findUserByUsername(request.getEmail());
        if (savedUser == null) throw new UserDoesNotExistsException("Email not yet registered.");
        else if (!Objects.equals(savedUser.getPassword(), request.getPassword())) throw new PasswordIncorrectException("This password is incorrect.");

        LoginUserResponse response = new LoginUserResponse();
        response.setMessage(String.format("Logged in successfully. Welcome back, %s!", request.getEmail()));

        return response;
    }
}
