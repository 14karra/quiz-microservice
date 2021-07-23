package quiz.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import quiz.entity.Role;
import quiz.entity.User;
import quiz.exception.AttemptToUpdateIdException;
import quiz.exception.UserNotFoundException;
import quiz.model.Response;
import quiz.repository.UserRepository;

import java.util.List;


@Service
public class UserService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        log.info("Getting all users");
        return userRepository.findAll();
    }

    public User getUser(String username) {
        log.info("Getting a user with username={}", username);
        return userRepository.findById(username).orElseThrow(() -> {
            log.warn("The user with username={} not found", username);
            return new UserNotFoundException(username);
        });
    }

    public User saveUser(User newUser) {
        newUser.setRole((Role.ADMIN));
        log.info("Saving a new user. The new user: {}", newUser.toString());
        return userRepository.save(newUser);
    }

    public User updateUser(User newUser, String username) {
        if (newUser.getUsername() != null && !username.equals(newUser.getUsername())) {
            throw new AttemptToUpdateIdException();
        }

        return userRepository.findById(username).map(user -> {
            log.info("Updating an user with username={}. New data: {}", username, newUser.toString());
            newUser.setUsername(user.getUsername());
            return userRepository.save(newUser);
        }).orElseThrow(() -> {
            log.warn("The user with username={} not found", username);
            return new UserNotFoundException(username);
        });
    }

    public Response deleteUser(String username) {
        log.info("Deleting an user with username={}", username);
        userRepository.deleteById(username);
        return new Response(HttpStatus.OK.value(), "User with username=" + username + " has been deleted");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
