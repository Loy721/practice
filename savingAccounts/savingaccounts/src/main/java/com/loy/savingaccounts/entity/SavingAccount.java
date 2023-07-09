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
    @GeneratedValue
    @Column(name = "user_id")
    private Integer userId;
//    @OneToMany(mappedBy = "savingAccountNumber", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<AccountOperation> accountOperations;
    private Integer number;
    private Float balance;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "number_card")
    private Card card;
}
