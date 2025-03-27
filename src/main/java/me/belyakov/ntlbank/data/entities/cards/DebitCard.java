package me.belyakov.ntlbank.data.entities.cards;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import me.belyakov.ntlbank.exceptions.economy.InsufficientFundsException;

import java.math.BigDecimal;

@Entity
@Table(name = "debit_cards")
public class DebitCard extends Card {

    public DebitCard(String number, String expirationDate, String CVP) {
        super(CardType.DEBIT, number, expirationDate, CVP);
    }

    @Override
    public void withdraw(BigDecimal sum) {
        if (balance.compareTo(sum) < 0) {
            throw new InsufficientFundsException();
        }
        this.balance = balance.subtract(sum);
    }
}
