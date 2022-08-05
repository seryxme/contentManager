package ng.hotsystems.contentManager.controllers;

import ng.hotsystems.contentManager.dtos.requests.LoginUserRequest;
import ng.hotsystems.contentManager.dtos.requests.RegisterUserRequest;
import org.springframework.http.ResponseEntity;

public interface UserController {
    ResponseEntity<?> registerUser(RegisterUserRequest request);

    ResponseEntity<?> loginUser(LoginUserRequest request);
}
