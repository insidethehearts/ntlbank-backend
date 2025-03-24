package me.belyakov.ntlban.web;

import jakarta.validation.Valid;
import me.belyakov.ntlban.services.implementations.UserServiceImpl;
import me.belyakov.ntlban.web.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    private UserServiceImpl userServiceImpl;

    public SignUpController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping(value = "/auth/sign-up", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> signUp(@RequestBody @Valid UserDTO userDTO) {
        if (userServiceImpl.isUserExists(userDTO)) {
            return new ResponseEntity<>("user exists", HttpStatus.OK);
        } else {
            userServiceImpl.registerUser(userDTO);
            return new ResponseEntity<>("user registered", HttpStatus.OK);
        }
    }

}