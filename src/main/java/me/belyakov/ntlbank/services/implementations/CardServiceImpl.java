package me.belyakov.ntlbank.services.implementations;

import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.data.entities.cards.Card;
import me.belyakov.ntlbank.data.entities.cards.CardType;
import me.belyakov.ntlbank.data.entities.cards.CreditCard;
import me.belyakov.ntlbank.data.entities.cards.DebitCard;
import me.belyakov.ntlbank.data.repositories.CardRepository;
import me.belyakov.ntlbank.exceptions.economy.CardNotFoundException;
import me.belyakov.ntlbank.services.CardService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card generateCardWithNumber(@NotNull CardType cardType, @NotNull UserEntity cardHolder) {
        Card card = cardType == CardType.DEBIT ? new DebitCard(cardHolder) : new CreditCard(cardHolder);
        card.setNumber(String.valueOf(cardRepository.generateCardNumber()));
        return card;
    }

    @Override
    public Card find(String number) {
        return cardRepository.findByNumber(number)
                .orElseThrow(() -> new CardNotFoundException(number));
    }

    @Override
    public Card find(String number, String expirationDate, String CVP, UserEntity cardHolder) {
        return cardRepository.findByNumberAndExpirationDateAndCVPAndCardHolder(number, expirationDate, CVP, cardHolder)
                .orElseThrow(() -> new CardNotFoundException(number));
    }

    @Override
    public List<Card> getCards(UserEntity userEntity) {
        return cardRepository.findByCardHolder(userEntity);
    }
}
