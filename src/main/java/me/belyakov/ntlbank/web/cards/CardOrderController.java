package me.belyakov.ntlbank.web.cards;

import me.belyakov.ntlbank.data.entities.UserEntity;
import me.belyakov.ntlbank.data.entities.cards.Card;
import me.belyakov.ntlbank.data.entities.cards.CardType;
import me.belyakov.ntlbank.data.entities.cards.DebitCard;
import me.belyakov.ntlbank.services.CardService;
import me.belyakov.ntlbank.services.JwtService;
import me.belyakov.ntlbank.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class CardOrderController {

    private UserService userService;
    private JwtService jwtService;
    private CardService cardService;

    public CardOrderController(UserService userService, JwtService jwtService, CardService cardService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.cardService = cardService;
    }

    @PostMapping(value = "/cards/order", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> orderCard(
            @RequestHeader(value = "X-Access-Token") String accessToken,
            @RequestBody Map<String, Object> requestBody,
            WebRequest request
    ) {
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("timestamp", LocalDateTime.now());

        CardType cardType = CardType.valueOf((String) requestBody.get("card_type"));
        UserEntity user = jwtService.userFromAccessJWT(accessToken);
        Card newCard;

        if (cardType.equals(CardType.DEBIT)) {
            newCard = cardService.generateCardWithNumber(CardType.DEBIT, user);
        } else {
            newCard = cardService.generateCardWithNumber(CardType.CREDIT, user);
        }

        user.getCards().add(newCard);
        userService.save(user);
        jsonResponse.put("card_type", newCard.getCardType().toString());
        jsonResponse.put("card_number", newCard.getNumber());
        jsonResponse.put("card_expirationDate", newCard.getExpirationDate());
        jsonResponse.put("card_cvp", newCard.getCVP());
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }
}