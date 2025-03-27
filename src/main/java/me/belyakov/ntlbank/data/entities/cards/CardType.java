package me.belyakov.ntlbank.data.entities.cards;

import lombok.Getter;

public enum CardType {
    DEBIT("Дебетовая карта"),
    CREDIT("Кредитная карта");

    @Getter
    private final String cardTypePresent;

    CardType(String cardTypePresent) {
        this.cardTypePresent = cardTypePresent;
    }
}