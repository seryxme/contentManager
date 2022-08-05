package ng.hotsystems.contentManager.controllers;

import ng.hotsystems.contentManager.dtos.requests.LoginUserRequest;
import ng.hotsystems.contentManager.dtos.requests.RegisterUserRequest;
import ng.hotsystems.contentManager.exceptions.PasswordIncorrectException;
import ng.hotsystems.contentManager.exceptions.UserDoesNotExistsException;
import ng.hotsystems.contentManager.exceptions.UserExistsException;
import ng.hotsystems.contentManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    @Override
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request) {
        try {
            return new ResponseEntity<>(userService.registerUser(request), HttpStatus.CREATED);
        } catch (UserExistsException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<?> loginUser(@RequestBody LoginUserRequest request) {
        try {
            return new ResponseEntity<>(userService.loginUser(request), HttpStatus.ACCEPTED);
        } catch (UserDoesNotExistsException | PasswordIncorrectException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
