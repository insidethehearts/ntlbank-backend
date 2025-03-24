package me.belyakov.ntlbank.services;

import me.belyakov.ntlbank.data.entities.UserEntity;

public interface JwtService {

    String generateAccess(UserEntity userEntity);
    String generateAccess(String refreshToken);
    String generateRefresh(UserEntity userEntity);

}