package me.belyakov.ntlbank.data.entities.cards;

import jakarta.persistence.Entity;
import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.exceptions.economy.WithdrawException;

import java.math.BigDecimal;

@Entity
public class DebitCard extends Card {

    public DebitCard() {
        init();
    }

    public DebitCard(UserEntity cardHolder) {
        super(CardType.DEBIT, cardHolder);
        init();
    }

    private void init() {
        this.balance = new BigDecimal(5000);
    }

    @Override
    public void withdraw(BigDecimal sum) throws WithdrawException {
        if (balance.compareTo(sum) < 0) {
            throw new WithdrawException(this.number, sum, balance);
        }
        this.balance = balance.subtract(sum);
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "balance=" + balance +
                ", number='" + number + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", CVP='" + CVP + '\'' +
                '}';
    }
}
