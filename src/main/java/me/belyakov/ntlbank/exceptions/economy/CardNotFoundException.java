package me.belyakov.ntlbank.exceptions.economy;

public class CardNotFoundException extends RuntimeException {

    public CardNotFoundException(String cardNumber) {
        super(String.format("Card with number '%s' and presented credentials not exists.", cardNumber));
    }
}