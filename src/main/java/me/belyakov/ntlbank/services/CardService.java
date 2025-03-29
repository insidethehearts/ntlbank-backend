package me.belyakov.ntlbank.services;

import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.data.entities.cards.Card;
import me.belyakov.ntlbank.data.entities.cards.CardType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface CardService {

    Card generateCardWithNumber(@NotNull CardType cardType, @NotNull UserEntity cardHolder);

    Optional<Card> find(String number);

    Optional<Card> find(String number, String expirationDate, String CVP);

}