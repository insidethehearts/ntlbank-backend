package me.belyakov.ntlbank.services.implementations;

import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.data.entities.cards.Card;
import me.belyakov.ntlbank.data.entities.cards.CardType;
import me.belyakov.ntlbank.data.entities.cards.CreditCard;
import me.belyakov.ntlbank.data.entities.cards.DebitCard;
import me.belyakov.ntlbank.data.repositories.CardRepository;
import me.belyakov.ntlbank.services.CardService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card generateCardWithNumber(@NotNull CardType cardType, @NotNull UserEntity cardHolder) {
        Card card = cardType == CardType.DEBIT ? new DebitCard(cardHolder) : new CreditCard(cardHolder); // TODO
        card.setNumber(String.valueOf(cardRepository.generateCardNumber()));
        return card;
    }

    @Override
    public Optional<Card> find(String number) {
        return Optional.empty(); // TODO
    }

    @Override
    public Optional<Card> find(String number, String expirationDate, String CVP) {
        return Optional.empty(); // TODO
    }
}
