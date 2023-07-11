package com.loy.savingaccounts.service;

import com.loy.savingaccounts.entity.SavingAccount;

import java.util.Optional;

public interface SavingAccountService {
    Boolean existsByNumber(String number);

    Boolean existByEmail(String eamil);

    SavingAccount save(SavingAccount savingAccount);

    Optional<SavingAccount> findByUserEmail(String id);

    Optional<SavingAccount> findByNumber(String  str);
}
