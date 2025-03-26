package me.belyakov.ntlbank.services.implementations;

import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.data.repositories.UserRepository;
import me.belyakov.ntlbank.exceptions.UserNotFoundException;
import me.belyakov.ntlbank.services.UserService;
import me.belyakov.ntlbank.web.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUserExists(UserDTO userDTO) {
        return userRepository
                .findByEmailOrPhoneOrPassportSeriesOrPassportNumber(
                        userDTO.getEmail(),
                        userDTO.getPhone(),
                        userDTO.getPassportSeries(),
                        userDTO.getPassportNumber()
                ).isPresent();
    }

    public UserEntity registerUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setSurname(userDTO.getSurname());
        userEntity.setName(userDTO.getName());
        userEntity.setPatronymic(userDTO.getPatronymic());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setPassportSeries(userDTO.getPassportSeries());
        userEntity.setPassportNumber(userDTO.getPassportNumber());
        userEntity.setPassword(userDTO.getPassword()); // TODO password protection
        userRepository.save(userEntity);
        return userEntity;
    }

    public UserEntity findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new UserNotFoundException(email, password));
    }

    public UserEntity findByPhoneAndPassword(String phone, String password) {
        return userRepository.findByPhoneAndPassword(phone, password).orElseThrow(() -> new UserNotFoundException(phone, password));
    }

}