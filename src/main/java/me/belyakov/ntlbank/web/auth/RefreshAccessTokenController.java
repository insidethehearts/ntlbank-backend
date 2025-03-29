package me.belyakov.ntlbank.web.auth;

import me.belyakov.ntlbank.services.JwtService;
import me.belyakov.ntlbank.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class RefreshAccessTokenController {

    private UserService userService;
    private JwtService jwtService;

    public RefreshAccessTokenController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/auth/refresh-access", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> refreshAccessToken(@RequestHeader(value = "X-Refresh-Token") String refreshToken, WebRequest request) {
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("timestamp", LocalDateTime.now());
        jsonResponse.put("access_token", jwtService.generateAccess(refreshToken));
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }
}