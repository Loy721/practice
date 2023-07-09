package com.loy.savingaccounts.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AccountOperation {
    @Id
    @GeneratedValue
    private Integer id;
    @JoinColumn(name = "saving_account_number")
    @ManyToOne(fetch = FetchType.LAZY)
    private SavingAccount savingAccountNumber;
    @JoinColumn(name = "to_saving_account_number")
    @ManyToOne(fetch = FetchType.LAZY)
    private SavingAccount toSavingAccountNumber;
    private LocalDateTime date;
    private Float amount;
    @Column(name = "type_operation")
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;
    @Column(name = "transfer_type")
    @Enumerated(EnumType.STRING)
    private TransferType transferType;
    private Float commission;
}
