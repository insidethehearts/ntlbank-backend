package me.belyakov.ntlbank.objects.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignInDTO {

    @JsonProperty(value = "email")
    @Email
    private String email;

    @JsonProperty(value = "phone")
    @Size(min = 10, max = 10)
    private String phone;

    @JsonProperty(value = "password", required = true)
    @NotEmpty
    @Size(min = 8, max = 128)
    private String password;

    public boolean isValid() {
        if (email != null && phone == null || phone != null && email == null)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "SignInDTO{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}