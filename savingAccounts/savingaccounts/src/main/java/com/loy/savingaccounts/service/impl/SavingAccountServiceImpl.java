package com.loy.savingaccounts.service.impl;

import com.loy.savingaccounts.entity.SavingAccount;
import com.loy.savingaccounts.repository.SavingAccountRepository;
import com.loy.savingaccounts.service.SavingAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SavingAccountServiceImpl implements SavingAccountService {

    private final SavingAccountRepository savingAccountRepository;

    @Override
    public SavingAccount save(SavingAccount savingAccount) {
        return savingAccountRepository.saveAndFlush(savingAccount);
    }

    @Override
    public Optional<SavingAccount> findByNumber(String num) {
        return savingAccountRepository.findByNumber(num);
    }

    @Override
    public Optional<SavingAccount> findByUserEmail(String id) {
        return savingAccountRepository.findByEmail(id);
    }

    @Override
    public Boolean existsByNumber(String number) {
        return savingAccountRepository.existsByNumber(number);
    }

    @Override
    public Boolean existByEmail(String email) {
        return savingAccountRepository.existsByEmail(email);
    }
}
