package me.belyakov.ntlbank.data.entities.cards;

import jakarta.persistence.*;
import lombok.Getter;
import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.exceptions.economy.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public abstract class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Getter
    @Column(nullable = false)
    private CardType cardType;

    @Getter
    @Column(nullable = false)
    protected BigDecimal balance;

    @Getter
    @Column(nullable = false)
    protected String number;

    @Getter
    @Column(nullable = false)
    protected String expirationDate;

    @Getter
    @Column(nullable = false)
    protected String CVP;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardHolderId")
    protected UserEntity cardHolder;

    public Card(CardType cardType, String number, String expirationDate, String CVP) {
        this.cardType = cardType;
        this.number = number;
        this.expirationDate = expirationDate;
        this.CVP = CVP;
        this.balance = new BigDecimal(0);
    }

    public abstract void withdraw(BigDecimal sum) throws InsufficientFundsException;

    public void deposit(BigDecimal sum) {
        this.balance = this.balance.add(sum);
    }

}