package com.loy.savingaccounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Card {
    @Id
    private String number;
    private LocalDate validity;
    @Column(nullable = false)
    private Integer cvc;
    @OneToOne(mappedBy = "card")
    private SavingAccount savingAccount;
}
