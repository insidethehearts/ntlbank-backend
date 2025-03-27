package me.belyakov.ntlbank.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import me.belyakov.ntlbank.data.entities.cards.Card;

import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String patronymic;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 10)
    private String phone;

    @Column(nullable = false, length = 4)
    private String passportSeries;

    @Column(nullable = false, length = 6)
    private String passportNumber;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "cardHolder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();
}