package me.belyakov.ntlbank.services;

import me.belyakov.ntlbank.exceptions.economy.InsufficientFundsException;
import me.belyakov.ntlbank.objects.dto.TransactionDTO;

public interface EconomyService {

    void processTransaction(TransactionDTO transactionDTO) throws InsufficientFundsException;

}