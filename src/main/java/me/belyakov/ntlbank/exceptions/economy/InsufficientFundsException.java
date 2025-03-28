package me.belyakov.ntlbank.exceptions.economy;

import me.belyakov.ntlbank.objects.dto.EconomyTransaction;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(EconomyTransaction economyTransaction) {
        super(String.format(
                "Failed to process economy transaction. <%s> -> [%s] -> <%s> ",
                    economyTransaction.senderId(),
                    economyTransaction.receiverId(),
                    economyTransaction.sum()
        ));
    }
}