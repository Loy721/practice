package com.loy.savingaccounts.service.impl;

import com.loy.savingaccounts.entity.AccountOperation;
import com.loy.savingaccounts.entity.SavingAccount;
import com.loy.savingaccounts.repository.AccountOperationRepository;
import com.loy.savingaccounts.service.AccountOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountOperationServiceImpl implements AccountOperationService {

    private final AccountOperationRepository accountOperationRepository;

    @Override
    public List<AccountOperation> getHistory(String num) {
        return accountOperationRepository.getAccountOperations(num);
    }

    @Override
    public AccountOperation addOperation(AccountOperation accountOperation) {
        return accountOperationRepository.save(accountOperation);
    }

    @Override
    public Integer getNextValSequence() {
        return accountOperationRepository.getNextValSequence();
    }
}
