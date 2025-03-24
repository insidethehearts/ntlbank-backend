package me.belyakov.ntlbank.web;

import jakarta.validation.Valid;
import me.belyakov.ntlbank.services.JwtService;
import me.belyakov.ntlbank.services.UserService;
import me.belyakov.ntlbank.web.dto.SignInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class SignInController {

    private UserService userService;
    private JwtService jwtService;

    public SignInController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/auth/sign-in", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> signIn(@RequestBody @Valid SignInDTO signInDTO, WebRequest webRequest) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}