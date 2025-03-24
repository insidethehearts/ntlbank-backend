package me.belyakov.ntlban.services.implementations;

import me.belyakov.ntlban.data.entities.UserEntity;
import me.belyakov.ntlban.data.repositories.UserRepository;
import me.belyakov.ntlban.web.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

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

    public void registerUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setSurname(userDTO.getSurname());
        userEntity.setName(userDTO.getName());
        userEntity.setPatronymic(userDTO.getPatronymic());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setPassportSeries(userDTO.getPassportSeries());
        userEntity.setPassportNumber(userDTO.getPassportNumber());
        userEntity.setPassword(userDTO.getPassword());
        userRepository.save(userEntity);
    }
}