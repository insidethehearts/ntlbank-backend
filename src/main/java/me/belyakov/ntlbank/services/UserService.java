package me.belyakov.ntlbank.services;

import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.web.dto.UserDTO;

public interface UserService {

    boolean isUserExists(UserDTO userDTO);

    UserEntity registerUser(UserDTO userDTO);

    UserEntity findByEmailAndPassword(String email, String password);

    UserEntity findByPhoneAndPassword(String phone, String password);

}