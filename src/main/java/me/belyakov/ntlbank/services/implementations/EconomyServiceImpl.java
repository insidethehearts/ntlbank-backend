package me.belyakov.ntlbank.services.implementations;

import me.belyakov.ntlbank.data.entities.cards.Card;
import me.belyakov.ntlbank.data.repositories.CardRepository;
import me.belyakov.ntlbank.exceptions.economy.CardNotFoundException;
import me.belyakov.ntlbank.exceptions.economy.InsufficientFundsException;
import me.belyakov.ntlbank.exceptions.economy.WithdrawException;
import me.belyakov.ntlbank.objects.dto.TransactionDTO;
import me.belyakov.ntlbank.services.EconomyService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EconomyServiceImpl implements EconomyService {

    private CardRepository cardRepository;

    public EconomyServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void processTransaction(TransactionDTO transactionDTO) throws WithdrawException {
        Card senderCard = cardRepository.findByNumberAndExpirationDateAndCVP(
                        transactionDTO.getOwnedCardNumber(),
                        transactionDTO.getOwnedCardExpirationDate(),
                        transactionDTO.getOwnedCardCVP()
                )
                .orElseThrow(() -> new CardNotFoundException(transactionDTO.getOwnedCardNumber()));

        Card receiverCard = cardRepository.findByNumber(transactionDTO.getTargetCardNumber())
                .orElseThrow(() -> new CardNotFoundException(transactionDTO.getTargetCardNumber()));

        try {
            senderCard.withdraw(new BigDecimal(transactionDTO.getSum()));
            receiverCard.deposit(new BigDecimal(transactionDTO.getSum()));
            cardRepository.save(senderCard);
            cardRepository.save(receiverCard);
        } catch (WithdrawException e) {
            throw new RuntimeException(e);
        }


        System.out.println(senderCard); // TODO remove this
        System.out.println(receiverCard); // TODO remove this
    }
}
