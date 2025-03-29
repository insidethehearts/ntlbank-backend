package me.belyakov.ntlbank.web;

import jakarta.validation.Valid;
import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.exceptions.economy.CardNotFoundException;
import me.belyakov.ntlbank.objects.dto.TransactionDTO;
import me.belyakov.ntlbank.services.EconomyService;
import me.belyakov.ntlbank.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class MoneyTransferController {

    private JwtService jwtService;
    private EconomyService economyService;

    public MoneyTransferController(JwtService jwtService, EconomyService economyService) {
        this.jwtService = jwtService;
        this.economyService = economyService;
    }

    @PostMapping(value = "/economy/transfer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> moneyTransfer(
            @RequestHeader("X-Access-Token") String accessToken,
            @RequestBody @Valid TransactionDTO transactionDTO
            ) {

        Map<String, Object> responseJson = new LinkedHashMap<>();
        responseJson.put("timestamp", LocalDateTime.now());

        UserEntity sender = jwtService.userFromAccessJWT(accessToken);
//        sender.getCards().stream().filter(card -> card.getNumber() == transactionDTO.getOwnedCardNumber())
//                        .findAny().orElseThrow(() -> new CardNotFoundException(transactionDTO.getOwnedCardNumber()));

        economyService.processTransaction(transactionDTO);

        responseJson.put("status", HttpStatus.OK.value());
        return new ResponseEntity<>(responseJson, HttpStatus.OK);
    }

}