package de.lesup.backend.controller;

import de.lesup.backend.api.User;
import de.lesup.backend.service.UserService;
import de.lesup.backend.model.UserEntity;
import de.lesup.backend.service.PasswordService;
import io.jsonwebtoken.lang.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static de.lesup.backend.controller.UserController.USER_CONTROLLER_TAG;
import static java.lang.String.format;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(name = USER_CONTROLLER_TAG, description = "Provides CRUD operations for an User")
@Api(
        tags = USER_CONTROLLER_TAG
)
@CrossOrigin
@RestController
@RequestMapping("/user")
@Getter
@Setter
public class UserController {
    /*

    @PutMapping("password")
    @ApiResponses(value = {
            @ApiResponse(code = SC_BAD_REQUEST, message = "User not found, or password empty")
    })
    public ResponseEntity<User> updatePassword(@AuthenticationPrincipal UserEntity authUser, @RequestBody NewPassword newPassword) {
        String password = newPassword.getPassword();
        Assert.hasText(password, "Password must not be blank");

        UserEntity updatedUserEntity = userService.updatePassword(authUser.getName(), password);
        User updatedUser = map(updatedUserEntity);
        updatedUser.setPassword(password);
        return ok(updatedUser);
    }

    @PutMapping("{name}/reset-password")
    @ApiResponses(value = {
            @ApiResponse(code = SC_NOT_FOUND, message = "User not found")
    })
    public ResponseEntity<User> resetPassword(@AuthenticationPrincipal UserEntity authUser, @PathVariable String name) {
        if (!authUser.getRole().equals("admin")) {
            throw new UnauthorizedUserException("User is not in required role 'admin'");
        }
        Optional<UserEntity> optionalUserEntity = userService.find(name);
        if (optionalUserEntity.isEmpty())
            throw new EntityNotFoundException(format("Unable to reset password for unknown user name=%s", name));

        String password = passwordService.getNewPassword();
        UserEntity updatedUserEntity = userService.updatePassword(name, password);

        User updatedUser = map(updatedUserEntity);
        updatedUser.setPassword(password);

        return ok(updatedUser);
    }

    @GetMapping(value = "{name}", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = SC_NOT_FOUND, message = "User not found")
    })
    public ResponseEntity<User> find(@PathVariable String name) {
        Optional<UserEntity> userEntityOptional = userService.find(name);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            User user = map(userEntity);
            return ok(user);
        }
        return notFound().build();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = SC_NO_CONTENT, message = "No users found")
    })
    public ResponseEntity<List<User>> findAll() {
        List<UserEntity> userEntities = userService.findAll();
        if (userEntities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<User> users = map(userEntities);
        return ok(users);
    }

    @DeleteMapping(value = "{name}", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = SC_NOT_FOUND, message = "User not found")
    })
    public ResponseEntity<User> delete(@AuthenticationPrincipal UserEntity authUser, @PathVariable String name) {
        if (authUser.getRole().equals("user") && !authUser.getName().equals(name)) {
            throw new UnauthorizedUserException("User in role 'user' must not delete an other user");
        }
        if (authUser.getRole().equals("admin") && authUser.getName().equals(name)) {
            throw new IllegalArgumentException("User in role 'admin' must delete itself");
        }

        Optional<UserEntity> userEntityOptional = userService.delete(name);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            User user = map(userEntity);
            return ok(user);
        }
        return notFound().build();
    }
*/
    private User map(UserEntity userEntity) {
        return User.builder()
                .username(userEntity.getUsername())
                .build();
    }

    private UserEntity map(User user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }

    public static final String USER_CONTROLLER_TAG = "User";
    private UserService userService;

    private PasswordService passwordService;

    @Autowired
    public UserController(UserService userService, PasswordService passwordService) {
        this.userService = userService;
        this.passwordService = passwordService;
    }
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = SC_BAD_REQUEST, message = "Unable to create User with blank name"),
            @ApiResponse(code = SC_CONFLICT, message = "Unable to create User, user already exists")
    })
    public ResponseEntity<User> create (@RequestBody User user) {

        UserEntity userEntity = map(user);
        UserEntity createdUserEntity = userService.create(userEntity);

        User createdUser = map(createdUserEntity);
        createdUser.setPassword(createdUserEntity.getPassword());

        return ok(createdUser);
    }

}