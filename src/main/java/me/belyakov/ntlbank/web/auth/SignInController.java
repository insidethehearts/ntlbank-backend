package me.belyakov.ntlbank.web.auth;

import jakarta.validation.Valid;
import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.services.JwtService;
import me.belyakov.ntlbank.services.UserService;
import me.belyakov.ntlbank.web.dto.SignInDTO;
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
public class SignInController {

    private UserService userService;
    private JwtService jwtService;

    public SignInController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/auth/sign-in", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> signIn(@RequestBody @Valid SignInDTO signInDTO, WebRequest request) {
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("timestamp", LocalDateTime.now());
        if (!signInDTO.isValid()) {
            jsonResponse.put("status", HttpStatus.BAD_REQUEST.value());
            jsonResponse.put("error", "Bad Request.");
            jsonResponse.put("path", request.getDescription(false).replace("uri=", ""));
            return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
        }
        UserEntity user;
        if (signInDTO.getEmail() != null) {
            user = userService.findByEmailAndPassword(signInDTO.getEmail(), signInDTO.getPassword());
        } else {
            user = userService.findByPhoneAndPassword(signInDTO.getPhone(), signInDTO.getPassword());
        }
        String refreshToken = jwtService.generateRefresh(user);
        String accessToken = jwtService.generateAccess(user);
        jsonResponse.put("status", HttpStatus.OK.value());
        jsonResponse.put("refresh_token", refreshToken);
        jsonResponse.put("access_token", accessToken);
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

}