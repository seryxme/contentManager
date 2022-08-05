package ng.hotsystems.contentManager.services;

import ng.hotsystems.contentManager.dtos.requests.LoginUserRequest;
import ng.hotsystems.contentManager.dtos.requests.RegisterUserRequest;
import ng.hotsystems.contentManager.dtos.responses.LoginUserResponse;
import ng.hotsystems.contentManager.dtos.responses.RegisterUserResponse;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest request);

    LoginUserResponse loginUser(LoginUserRequest request);
}
