package me.belyakov.ntlban.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UserDTO  {

    private static final String INITIALS_REGEX = "^[А-ЯЁA-Z][а-яёa-z]+([-' ][А-ЯЁA-Zа-яёa-z]+)*$";

    @JsonProperty(value = "surname", required = true)
    @NotEmpty
    @Pattern(regexp = INITIALS_REGEX)
    @Size(min = 2, max = 15)
    private String surname;

    @JsonProperty(value = "name", required = true)
    @NotEmpty
    @Pattern(regexp = INITIALS_REGEX)
    @Size(min = 2, max = 23)
    private String name;

    @JsonProperty(value = "patronymic", required = true)
    @NotEmpty
    @Pattern(regexp = INITIALS_REGEX)
    @Size(min = 2, max = 32)
    private String patronymic;

    @JsonProperty(value = "email", required = true)
    @NotEmpty
    @Email
    private String email;

    @JsonProperty(value = "phone", required = true)
    @NotEmpty
    @Size(min = 10, max = 10)
    private String phone;

    @JsonProperty(value = "passport_series", required = true)
    @NotEmpty
    @Size(min = 4, max = 4)
    private String passportSeries;

    @JsonProperty(value = "passport_number", required = true)
    @NotEmpty
    @Size(min = 6, max = 6)
    private String passportNumber;

    @JsonProperty(value = "password", required = true)
    @NotEmpty
    @Size(min = 8, max = 128)
    private String password;

    @Override
    public String toString() {
        return "UserDTO{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passportSeries=" + passportSeries +
                ", passportNumber=" + passportNumber +
                ", password='" + password + '\'' +
                '}';
    }
}