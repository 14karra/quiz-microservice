package quiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import quiz.entity.User;
import quiz.model.Response;
import quiz.service.EncoderService;
import quiz.service.UserService;

import java.util.List;

@RestController
@RequestMapping(UserController.PATH)
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public static final String PATH = "/api/quiz/users";

    private final UserService userService;

    private final EncoderService encoderService;

    public UserController(UserService userService, EncoderService encoderService) {
        this.userService = userService;
        this.encoderService = encoderService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        log.info("Request to get all users is received");
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{username}")
    public User getUser(@PathVariable String username) {
        log.info("Request to get a user with username={} is received", username);
        return userService.getUser(username);
    }

    @PutMapping(value = "/{username}")
    public User updateUser(@RequestBody User newUser, @PathVariable String username) {
        log.info("Request to update a user with username={} is received. User: {}", username, newUser.toString());
        return userService.updateUser(newUser, username);
    }

    @PostMapping(value = "/registration")
    public User saveUser(@RequestBody User newUser) {
        log.info("Request to save a new user is received. New user: {}", newUser.toString());
        newUser.setPassword(encoderService.encode(newUser.getPassword()));
        return userService.saveUser(newUser);
    }

    @DeleteMapping(value = "/{username}")
    public Response deleteUser(@PathVariable String username) {
        log.info("Request to delete a user with username={} is received", username);
        return userService.deleteUser(username);
    }
}
