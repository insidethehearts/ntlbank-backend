package me.belyakov.ntlbank.services.implementations;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.data.repositories.UserRepository;
import me.belyakov.ntlbank.exceptions.BadRefreshJWTException;
import me.belyakov.ntlbank.services.JwtService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtServiceImpl implements JwtService {

    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;

    private UserRepository userRepository;

    public JwtServiceImpl(UserRepository userRepository) {
        this.algorithm = Algorithm.HMAC512("SECRET_HERE");
        this.jwtVerifier = JWT.require(algorithm).build();
        this.userRepository = userRepository;
    }

    @Override
    public String generateAccess(UserEntity userEntity) {
        return JWT
                .create()
                .withClaim("uid", userEntity.getId())
                .withClaim("surname", userEntity.getSurname())
                .withClaim("name", userEntity.getName())
                .withClaim("patronymic", userEntity.getPatronymic())
                .withExpiresAt(Instant.now().plus(15, ChronoUnit.MINUTES))
                .sign(algorithm);
    }

    @Override
    public String generateAccess(String refreshToken) {
        DecodedJWT decodedRefresh = decode(refreshToken);
        if (decodedRefresh.getClaims().containsKey("uid")) throw new BadRefreshJWTException();
        UserEntity userEntity = userRepository.findById(decodedRefresh.getClaim("uid").asLong()).orElseThrow(() -> new BadRefreshJWTException("Bad user id."));
        return generateAccess(userEntity);
    }

    @Override
    public String generateRefresh(UserEntity userEntity) {
        return JWT
                .create()
                .withClaim("uid", userEntity.getId())
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                .sign(algorithm);
    }

    private DecodedJWT decode(String token) throws JWTVerificationException {
        return jwtVerifier.verify(token);
    }
}
