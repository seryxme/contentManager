package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.data.models.User;
import ng.hotsystems.contentManager.data.reporsitories.UserRepository;
import ng.hotsystems.contentManager.dtos.requests.RegisterUserRequest;
import ng.hotsystems.contentManager.dtos.responses.RegisterUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        User user = new User();
        user.setUsername(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        RegisterUserResponse response = new RegisterUserResponse();
        response.setMessage(String.format("%s successfully registered.", request.getEmail()));
        return response;
    }
}
