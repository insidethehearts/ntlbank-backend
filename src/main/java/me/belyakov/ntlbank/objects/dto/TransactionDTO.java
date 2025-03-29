package me.belyakov.ntlbank.objects.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class TransactionDTO {

    @NotEmpty
    @Size(min = 16, max = 16)
    @JsonProperty(value = "owned_card_number", required = true)
    private String ownedCardNumber;

    @NotEmpty
    @Size(min = 4, max = 4)
    @JsonProperty(value = "owned_card_expiration_date", required = true)
    private String ownedCardExpirationDate;

    @NotEmpty
    @Size(min = 3, max = 3)
    @JsonProperty(value = "owned_card_cvp", required = true)
    private String ownedCardCVP;

    @NotEmpty
    @Size(min = 16, max = 16)
    @JsonProperty(value = "target_card_number", required = true)
    private String targetCardNumber;

    // TODO add checks
    @JsonProperty(value = "sum", required = true)
    private Long sum;

}