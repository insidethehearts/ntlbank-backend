package me.belyakov.ntlbank.data.repositories;

import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.data.entities.cards.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {

    Card save(Card entity);

    Optional<Card> findByNumber(String number);

    Optional<Card> findByNumberAndExpirationDateAndCVP(String number, String expirationDate, String CVP);

    List<Card> findByCardHolder(UserEntity cardHolder);
}