package me.belyakov.ntlbank.data.entities.cards;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.services.CardService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
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

    @Getter @Setter
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

    public Card() {
        init();
    }

    public Card(CardType cardType, UserEntity cardHolder) {
        this.cardType = cardType;
        this.cardHolder = cardHolder;

        init();
    }

    private void init() {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plusYears(4);
        this.expirationDate = DateTimeFormatter.ofPattern("MMyy").format(localDateTime);

        Random random = new Random();
        this.CVP = String.format("%s%s%s", random.nextInt(10), random.nextInt(10), random.nextInt(10));

        this.balance = new BigDecimal(0);
    }

    public abstract boolean withdraw(BigDecimal sum);

    public void deposit(BigDecimal sum) {
        this.balance = this.balance.add(sum);
    }
}