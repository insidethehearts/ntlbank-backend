package me.belyakov.ntlbank.web;

import jakarta.validation.Valid;
import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.services.JwtService;
import me.belyakov.ntlbank.services.UserService;
import me.belyakov.ntlbank.web.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class SignUpController {

    private UserService userService;
    private JwtService jwtService;

    public SignUpController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/auth/sign-up", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> signUp(@RequestBody @Valid UserDTO userDTO, WebRequest request) {
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("timestamp", LocalDateTime.now());
        jsonResponse.put("path", request.getDescription(false).replace("uri=", ""));

        if (userService.isUserExists(userDTO)) {
            jsonResponse.put("status", HttpStatus.CONFLICT.value());
            jsonResponse.put("message", "User with that data already exists.");
            return new ResponseEntity<>(jsonResponse, HttpStatus.CONFLICT);
        } else {
            UserEntity user = userService.registerUser(userDTO);
            jsonResponse.put("status", HttpStatus.OK);
            jsonResponse.put("refresh_token", jwtService.generateRefresh(user));
            jsonResponse.put("access_token", jwtService.generateAccess(user));
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }
    }

}