package com.loy.savingaccounts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "saving_accounts")
@NoArgsConstructor
@Getter
@Setter
public class SavingAccount {
    @Id
    @Column(name = "user_email")
    private String email;
//    @OneToMany(mappedBy = "savingAccountNumber", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<AccountOperation> accountOperations;
    @Column(nullable = false,unique = true)
    private String number;
    private Double balance;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "number_card")
    private Card card;
}
