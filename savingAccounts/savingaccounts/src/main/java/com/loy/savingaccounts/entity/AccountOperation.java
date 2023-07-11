package com.loy.savingaccounts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccountOperation {
    @Id
    @GeneratedValue
    private Integer id;
    @JoinColumn(name = "saving_account_number", nullable = false, referencedColumnName = "number")
    @ManyToOne(fetch = FetchType.LAZY)
    private SavingAccount savingAccountNumber;
    @JoinColumn(name = "to_saving_account_number", referencedColumnName = "number")
    @ManyToOne(fetch = FetchType.LAZY)
    private SavingAccount toSavingAccountNumber;
    private Double amount;
    private Double balance;
    private LocalDateTime date;
    @Column(name = "type_operation")
    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;
    @Column(name = "transfer_type")
    @Enumerated(EnumType.STRING)
    private TransferType transferType;
    private Double commission;
}
