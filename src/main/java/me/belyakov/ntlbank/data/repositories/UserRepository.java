package me.belyakov.ntlbank.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import me.belyakov.ntlbank.data.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity save(UserEntity entity);

    Optional<UserEntity> findByEmailOrPhoneOrPassportSeriesOrPassportNumber(String email, String Phone, String passportSeries, String passportNumber);
}