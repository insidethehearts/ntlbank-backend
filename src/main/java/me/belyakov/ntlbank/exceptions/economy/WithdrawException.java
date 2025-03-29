package me.belyakov.ntlbank.exceptions.economy;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class WithdrawException extends RuntimeException {

    private final BigDecimal sum;
    private final BigDecimal allowedSum;

    public WithdrawException(String cardNumber, BigDecimal sum, BigDecimal allowedSum) {
        super(String.format("There are not enough funds on bank card '%s' to pay.", cardNumber));
        this.sum = sum;
        this.allowedSum = allowedSum;
    }
}