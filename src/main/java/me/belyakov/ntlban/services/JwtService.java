package me.belyakov.ntlban.services;

import me.belyakov.ntlban.data.entities.UserEntity;

public interface JwtService {

    String generateAccess(UserEntity userEntity);
    String generateAccess(String refreshToken);
    String generateRefresh(UserEntity userEntity);

}