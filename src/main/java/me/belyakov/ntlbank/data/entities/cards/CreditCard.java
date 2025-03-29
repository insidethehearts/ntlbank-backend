package me.belyakov.ntlbank.data.entities.cards;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.exceptions.economy.InsufficientFundsException;
import me.belyakov.ntlbank.exceptions.economy.WithdrawException;

import java.math.BigDecimal;

@Entity
public class CreditCard extends Card {

    @Getter
    @Column
    private BigDecimal creditLimit;

    public CreditCard() {
        init();
    }

    public CreditCard(UserEntity cardHolder) {
        super(CardType.CREDIT, cardHolder);
        init();
    }

    private void init() {
        this.creditLimit = new BigDecimal(100000);
    }

    @Override
    public void withdraw(BigDecimal sum) throws WithdrawException {
        BigDecimal allowedSum = this.balance.add(creditLimit);
        if (allowedSum.compareTo(sum) < 0) {
            throw new WithdrawException(this.number, sum, allowedSum);
        }
        this.balance = this.balance.subtract(sum);
    }


    @Override
    public String toString() {
        return "CreditCard{" +
                "CVP='" + CVP + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                ", creditLimit=" + creditLimit +
                '}';
    }
}