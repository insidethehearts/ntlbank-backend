package me.belyakov.ntlbank.objects.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import me.belyakov.ntlbank.data.entities.cards.CardType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;

@Getter
public class CardReverseDTO {

    @NotNull
    @JsonProperty("card_type")
    private final CardType cardType;

    @NotNull
    private final String number;

    @NotNull
    @JsonProperty("expiration_date")
    private final String expirationDate;

    @NotNull
    private final String cvp;

    @Nullable
    private final BigDecimal balance;

    @Nullable
    @JsonProperty("credit_limit")
    private final BigDecimal creditLimit;

    public CardReverseDTO(@NotNull CardType cardType, @NotNull String number, @NotNull String expirationDate, @NotNull String cvp, @Nullable BigDecimal balance, @Nullable BigDecimal creditLimit) {
        this.cardType = cardType;
        this.number = number;
        this.expirationDate = expirationDate;
        this.cvp = cvp;
        this.balance = balance;
        this.creditLimit = creditLimit;
    }
}