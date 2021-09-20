package de.lesup.backend.controller;

import de.lesup.backend.api.AccessToken;
import de.lesup.backend.api.Credentials;
import de.lesup.backend.model.UserEntity;
import de.lesup.backend.service.JwtService;
import de.lesup.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static de.lesup.backend.controller.AuthController.AUTH_TAG;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Tag(name = AUTH_TAG, description = "Provides login and principal information")
@Api(
        tags = AUTH_TAG
)
@RestController
public class AuthController {
    public static final String AUTH_TAG = "AUTH";
    public static final String ACCESS_TOKEN_URL = "/auth/access_token";

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Operation(summary = "Create JWT Token by credentials.")
    @PostMapping(value = ACCESS_TOKEN_URL, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = SC_BAD_REQUEST, message = "Username or password blank"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = "Username or password is wrong")
    })
    public ResponseEntity<AccessToken> getAccessToken(@RequestBody Credentials credentials) {
        String username = credentials.getUsername();
        //hastext später hinzufügen, wegen illegal Argument exception
        String password = credentials.getPassword();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

        try {
            authenticationManager.authenticate(authToken);

            UserEntity user = userService.findByUsername(username).orElseThrow();
            String token = jwtService.createJwtToken(user);

            AccessToken accessToken = new AccessToken(token);
            return ok(accessToken);

        } catch (AuthenticationException ex) {
            return new ResponseEntity<>(UNAUTHORIZED);
        }
    }
}
