package me.belyakov.ntlbank.exceptions.economy;

import me.belyakov.ntlbank.objects.dto.TransactionDTO;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(TransactionDTO transactionDTO) {
        super(String.format(
                "Failed to process economy transaction. <%s> -> [%s] -> <%s> ",
                    transactionDTO.getOwnedCardNumber(),
                    transactionDTO.getSum(),
                    transactionDTO.getTargetCardNumber()
        ));
    }
}