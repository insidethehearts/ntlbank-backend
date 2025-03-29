package me.belyakov.ntlbank.services;

import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.data.entities.cards.Card;
import me.belyakov.ntlbank.data.entities.cards.CardType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CardService {

    Card generateCardWithNumber(@NotNull CardType cardType, @NotNull UserEntity cardHolder);

    Card find(String number);

    Card find(String number, String expirationDate, String CVP, UserEntity cardHolder);

    List<Card> getCards(UserEntity userEntity);
}