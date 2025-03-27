package me.belyakov.ntlbank.exceptions.economy;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(EconomyTransaction economyTransaction) {
        super();
    }
}