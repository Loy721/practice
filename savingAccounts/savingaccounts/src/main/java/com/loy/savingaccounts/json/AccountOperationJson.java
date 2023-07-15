package com.loy.savingaccounts.json;

import com.loy.savingaccounts.entity.TransferType;
import com.loy.savingaccounts.entity.TypeOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class AccountOperationJson {
    private String savingAccountNumber;
    private String toSavingAccountNumber;
    private LocalDateTime date;
    private Double balance;
    private Double amount;
    private TypeOperation typeOperation;
    private TransferType transferType;
    private Double commission;
}
