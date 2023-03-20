package com.pamela.todoapp.controller;

import com.pamela.todoapp.dto.User;
import com.pamela.todoapp.request.TokenRequest;
import com.pamela.todoapp.response.GetTokenResponse;
import com.pamela.todoapp.security.TokenService;
import com.pamela.todoapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
class AuthController {

    private final TokenService tokenService;

    private final UserService userService;

    @Operation(
            tags = "Authentication",
            summary = "Generate authorization token"
    )
    @PostMapping("/token")
    public ResponseEntity<GetTokenResponse> token(@RequestBody TokenRequest tokenRequest) {

        log.info("Token requested for user: {}", tokenRequest.getEmail());

        if (credentialsValid(tokenRequest.getEmail(), tokenRequest.getPassword())) {
            String token = tokenService.generateToken(tokenRequest.getEmail());
            log.info("Token granted {}", token);
            return new ResponseEntity<>(new GetTokenResponse(token, tokenRequest.getEmail()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean credentialsValid(String email, String password) {
        final Optional<User> user = userService.getUser(email);

        if (user.isEmpty()) {
            return false;
        }

        return password.equals(user.get().getPassword());
    }
}
