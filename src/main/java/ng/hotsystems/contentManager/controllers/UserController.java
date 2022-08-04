package ng.hotsystems.contentManager.controllers;

import ng.hotsystems.contentManager.dtos.requests.RegisterUserRequest;
import ng.hotsystems.contentManager.dtos.responses.RegisterUserResponse;

public interface UserController {
    RegisterUserResponse registerUser(RegisterUserRequest request);
}
