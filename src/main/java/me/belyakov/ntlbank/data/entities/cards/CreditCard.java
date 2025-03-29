package me.belyakov.ntlbank.data.entities.cards;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import me.belyakov.ntlbank.data.entities.UserEntity;

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
    public boolean withdraw(BigDecimal sum) {
        return this.balance.add(creditLimit).compareTo(sum) < 0;
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