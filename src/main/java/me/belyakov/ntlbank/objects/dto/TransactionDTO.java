package me.belyakov.ntlbank.objects.dto;

import java.math.BigDecimal;

public record TransactionDTO(String senderCard, String receiverCard, BigDecimal sum) {}