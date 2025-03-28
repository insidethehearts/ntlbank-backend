package me.belyakov.ntlbank.services.implementations;

import me.belyakov.ntlbank.data.entities.cards.Card;
import me.belyakov.ntlbank.data.repositories.CardRepository;
import me.belyakov.ntlbank.exceptions.economy.CardNotFoundException;
import me.belyakov.ntlbank.exceptions.economy.InsufficientFundsException;
import me.belyakov.ntlbank.objects.dto.TransactionDTO;
import me.belyakov.ntlbank.services.EconomyService;
import org.springframework.stereotype.Service;

@Service
public class EconomyServiceImpl implements EconomyService {

    private CardRepository cardRepository;

    public EconomyServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void processTransaction(TransactionDTO transactionDTO) throws InsufficientFundsException {
        Card senderCard = cardRepository.findByNumber(transactionDTO.senderCard())
                .orElseThrow(() -> new CardNotFoundException(transactionDTO.senderCard()));
        Card receiverCard = cardRepository.findByNumber(transactionDTO.receiverCard())
                .orElseThrow(() -> new CardNotFoundException(transactionDTO.receiverCard()));

        try {
           senderCard.withdraw(transactionDTO.sum());
        } catch (InsufficientFundsException e) {
            throw new RuntimeException(e);
        }
        receiverCard.deposit(transactionDTO.sum());
    }
}
