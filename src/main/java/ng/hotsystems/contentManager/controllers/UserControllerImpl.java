package ng.hotsystems.contentManager.controllers;

import ng.hotsystems.contentManager.dtos.requests.*;
import ng.hotsystems.contentManager.dtos.responses.AddCommentResponse;
import ng.hotsystems.contentManager.exceptions.*;
import ng.hotsystems.contentManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> createBlog(@RequestBody CreateBlogRequest request) {
        try {
            return new ResponseEntity<>(userService.createBlog(request), HttpStatus.CREATED);
        } catch (BlogExistsException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/blog")
    @Override
    public ResponseEntity<?> addArticle(@RequestBody AddArticleRequest request) {
        try {
            return new ResponseEntity<>(userService.addArticle(request), HttpStatus.CREATED);
        } catch (ArticleExistsException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/blog")
    @Override
    public ResponseEntity<?> deleteArticle(@RequestBody DeleteArticleRequest request) {
        try {
            return new ResponseEntity<>(userService.deleteArticle(request), HttpStatus.ACCEPTED);
        } catch (ArticleDoesNotExistException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/blog/{blogName}")
    @Override
    public ResponseEntity<?> viewBlog(@PathVariable String blogName) {
        try {
            return new ResponseEntity<>(userService.viewBlog(blogName), HttpStatus.ACCEPTED);
        } catch (BlogDoesNotExistException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{blogName}/{articleTitle}")
    @Override
    public ResponseEntity<?> viewArticle(@PathVariable String blogName, @PathVariable String articleTitle) {
        FindArticleRequest request = new FindArticleRequest();
        request.setTitle(articleTitle);
        request.setBlogName(blogName);

        try {
            return new ResponseEntity<>(userService.viewArticle(request), HttpStatus.ACCEPTED);
        } catch (ArticleDoesNotExistException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{blogName}/{articleTitle}")
    @Override
    public AddCommentResponse addComment(@RequestBody AddCommentRequest request, @PathVariable String blogName, @PathVariable String articleTitle) {
        request.setArticleTitle(articleTitle);
        request.setBlogName(blogName);

        return userService.addComment(request);
    }
}
