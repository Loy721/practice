package com.loy.savingaccounts.domain;

import com.loy.savingaccounts.entity.AccountOperation;
import com.loy.savingaccounts.entity.SavingAccount;
import com.loy.savingaccounts.entity.TransferType;
import com.loy.savingaccounts.entity.TypeOperation;
import com.loy.savingaccounts.json.AccountOperationJson;
import com.loy.savingaccounts.service.AccountOperationService;
import com.loy.savingaccounts.service.SavingAccountService;
import com.loy.savingaccounts.util.NumberGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavingAccountOperationService {

    private final NumberGeneratorUtil numberGenerator;

    private final SavingAccountService savingAccountService;

    private final AccountOperationService accountOperationService;

    @Transactional
    public AccountOperation saveOperation(SavingAccount fromSavingAccount, SavingAccount toSavingAccount,
                                          Double sum, TypeOperation typeOperation, Double balance) {
        AccountOperation accountOperation = new AccountOperation(accountOperationService.getNextValSequence(),
                fromSavingAccount, toSavingAccount, balance, sum, LocalDateTime.now(), typeOperation,
                TransferType.SAVING_ACCOUNT, 0.);
        return accountOperationService.addOperation(accountOperation);
    }

    public List<AccountOperationJson> getHistory(Principal principal) {

        SavingAccount savingAccount = savingAccountService.findByUserEmail(principal.getName()).orElseThrow();
        return accountOperationService.getHistory(savingAccount.getNumber()).stream()
                .map(x -> new AccountOperationJson(x.getSavingAccountNumber().getNumber(),
                        x.getToSavingAccountNumber() == null ? null: x.getToSavingAccountNumber().getNumber(),
                        x.getDate(), x.getBalance(), x.getAmount(), x.getTypeOperation(),
                        x.getTransferType(), x.getCommission())).collect(Collectors.toList());
    }

    public void createSavingAccount(Principal principal){
        if (!savingAccountService.existByEmail(principal.getName())) {
            SavingAccount savingAccount = new SavingAccount();
            savingAccount.setEmail(principal.getName());
            savingAccount.setNumber(numberGenerator.generateUniqueStringOfNumberForSavingAccount());
            savingAccount.setBalance(0.);
            savingAccountService.save(savingAccount);
        } else
            throw new RuntimeException("Saving account already exists");
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Double replenishment(Principal principal, Double sum) {

        SavingAccount savingAccount = savingAccountService.findByUserEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Saving account does not exists"));
        savingAccount.setBalance(savingAccount.getBalance() + sum);

        saveOperation(savingAccount, null, sum,
                TypeOperation.REPLENISHMENT, savingAccount.getBalance());

        return sum;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Double transfer(Principal principal, String number, Double sum) {
        SavingAccount fromSavingAccount = savingAccountService.findByUserEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Saving account does not exists"));

        if(fromSavingAccount.getNumber().equals(number))
            throw new RuntimeException("Unsupported");
        if (fromSavingAccount.getBalance() < sum)
            throw new RuntimeException("Insufficient funds");
        fromSavingAccount.setBalance(fromSavingAccount.getBalance() - sum);
        SavingAccount toSavingAccount = savingAccountService.findByNumber(number)
                .orElseThrow(() -> new RuntimeException("Saving account does not exists"));
        toSavingAccount.setBalance(toSavingAccount.getBalance() + sum);

        saveOperation(fromSavingAccount, toSavingAccount, sum,
                TypeOperation.TRANSFER, fromSavingAccount.getBalance());

        return sum;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Double pay(Principal principal, Double sum) {

        SavingAccount savingAccount = savingAccountService.findByUserEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Saving account does not exists"));
        if (savingAccount.getBalance() < sum)
            throw new RuntimeException("Insufficient funds");
        savingAccount.setBalance(savingAccount.getBalance() - sum);
        saveOperation(savingAccount, null, sum,
                TypeOperation.PAY, savingAccount.getBalance());

        return sum;
    }
}
