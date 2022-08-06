package ng.hotsystems.contentManager.controllers;

import ng.hotsystems.contentManager.dtos.requests.*;
import ng.hotsystems.contentManager.dtos.responses.*;
import ng.hotsystems.contentManager.exceptions.PasswordIncorrectException;
import ng.hotsystems.contentManager.exceptions.UserDoesNotExistException;
import ng.hotsystems.contentManager.exceptions.UserExistsException;
import ng.hotsystems.contentManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        } catch (UserDoesNotExistException | PasswordIncorrectException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/user")
    @Override
    public CreateBlogResponse createBlog(@RequestBody CreateBlogRequest request) {
        return userService.createBlog(request);
    }

    @PostMapping("/blog")
    @Override
    public AddArticleResponse addArticle(@RequestBody AddArticleRequest request) {
        return userService.addArticle(request);
    }

    @DeleteMapping("/blog")
    @Override
    public DeleteArticleResponse deleteArticle(@RequestBody DeleteArticleRequest request) {
        return userService.deleteArticle(request);
    }

    @GetMapping("/blog/{blogName}")
    @Override
    public List<FindBlogArticlesResponse> viewBlog(@PathVariable String blogName) {
        return userService.viewBlog(blogName);
    }

    @GetMapping("/{blogName}/{articleTitle}")
    @Override
    public FindArticleResponse viewArticle(@PathVariable String blogName, @PathVariable String articleTitle) {
        FindArticleRequest request = new FindArticleRequest();
        request.setTitle(articleTitle);
        request.setBlogName(blogName);
        return userService.viewArticle(request);
    }
}
