package me.belyakov.ntlbank.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

public class SignInDTO {

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "phone")
    @Size(min = 10, max = 10)
    private String phone;

    @JsonProperty(value = "password")
    @Size(min = 8, max = 128)
    private String password;

    @Override
    public String toString() {
        return "SignInDTO{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}