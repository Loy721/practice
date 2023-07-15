package com.loy.savingaccounts.repository;

import com.loy.savingaccounts.entity.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SavingAccountRepository extends JpaRepository<SavingAccount, Integer> {
    Boolean existsByNumber(String number);

    Boolean existsByEmail(String email);

    Optional<SavingAccount> findByEmail(String id);

    Optional<SavingAccount> findByNumber(String str);
}
