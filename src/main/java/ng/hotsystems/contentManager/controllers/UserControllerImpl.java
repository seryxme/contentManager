package ng.hotsystems.contentManager.controllers;

import ng.hotsystems.contentManager.dtos.requests.RegisterUserRequest;
import ng.hotsystems.contentManager.dtos.responses.RegisterUserResponse;
import ng.hotsystems.contentManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    @Override
    public RegisterUserResponse registerUser(@RequestBody RegisterUserRequest request) {
        return userService.registerUser(request);
    }
}
