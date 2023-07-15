package com.loy.savingaccounts.service;

import com.loy.savingaccounts.entity.AccountOperation;
import com.loy.savingaccounts.entity.SavingAccount;

import java.util.List;

public interface AccountOperationService {
    List<AccountOperation> getHistory(String num);

    AccountOperation addOperation(AccountOperation accountOperation);

    Integer getNextValSequence();
}
