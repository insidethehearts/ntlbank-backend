package me.belyakov.ntlbank.web.cards;

import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.data.entities.cards.CardType;
import me.belyakov.ntlbank.data.entities.cards.CreditCard;
import me.belyakov.ntlbank.objects.dto.CardReverseDTO;
import me.belyakov.ntlbank.services.CardService;
import me.belyakov.ntlbank.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GetCardsController {

    private final JwtService jwtService;
    private final CardService cardService;

    public GetCardsController(CardService cardService, JwtService jwtService) {
        this.cardService = cardService;
        this.jwtService = jwtService;
    }

    @GetMapping(value = "/cards/overview", produces = "application/json")
    public ResponseEntity<Object> getCards(
            @RequestHeader(value = "X-Access-Token") String accessToken
    ) {
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("timestamp", LocalDateTime.now());
        UserEntity userEntity = jwtService.userFromAccessJWT(accessToken);
        List<CardReverseDTO> cardList = cardService.getCards(userEntity).stream().map(nativeCard -> new CardReverseDTO(
                nativeCard.getCardType(),
                nativeCard.getNumber(),
                nativeCard.getExpirationDate(),
                nativeCard.getCVP(),
                nativeCard.getBalance(),
                (nativeCard.getCardType() == CardType.DEBIT) ? null : ((CreditCard) nativeCard).getCreditLimit()
        )).toList();
        jsonResponse.put("cards", cardList);
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

}