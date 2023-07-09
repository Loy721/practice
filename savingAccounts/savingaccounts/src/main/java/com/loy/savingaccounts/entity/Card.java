package com.loy.savingaccounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class Card {
    @Id
    @OneToOne
    private SavingAccount number;
    private LocalDate validity;
    private Integer cvc;
}
