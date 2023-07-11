package com.loy.savingaccounts.rest;

import com.loy.savingaccounts.entity.AccountOperation;
import com.loy.savingaccounts.entity.SavingAccount;
import com.loy.savingaccounts.entity.TransferType;
import com.loy.savingaccounts.entity.TypeOperation;
import com.loy.savingaccounts.json.AccountOperationJson;
import com.loy.savingaccounts.service.AccountOperationService;
import com.loy.savingaccounts.service.SavingAccountService;
import com.loy.savingaccounts.util.NumberGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/saving-account")
@RequiredArgsConstructor
public class SavingAccountController {

    private final NumberGeneratorUtil numberGenerator;

    private final SavingAccountService savingAccountService;

    private final AccountOperationService accountOperationService;

    private AccountOperation saveOperation(SavingAccount fromSavingAccount, SavingAccount toSavingAccount,
                                           Double sum, TypeOperation typeOperation, Double balance) {
        AccountOperation accountOperation = new AccountOperation(accountOperationService.getNextValSequence(),
                fromSavingAccount, toSavingAccount, balance, sum, LocalDateTime.now(), typeOperation,
                TransferType.SAVING_ACCOUNT, 0.);
        return accountOperationService.addOperation(accountOperation);
    }

    @GetMapping("/history")
    public List<AccountOperationJson> getAccountHistory(Principal principal) {

        SavingAccount savingAccount = savingAccountService.findByUserEmail(principal.getName()).orElseThrow();
        return accountOperationService.getHistory(savingAccount.getNumber()).stream()
                .map(x -> new AccountOperationJson(x.getSavingAccountNumber().getNumber(),
                        x.getToSavingAccountNumber() == null ? null: x.getToSavingAccountNumber().getNumber(),
                        x.getDate(), x.getBalance(), x.getAmount(), x.getTypeOperation(),
                        x.getTransferType(), x.getCommission())).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> createSavingAccount(Principal principal) {
        if (!savingAccountService.existByEmail(principal.getName())) {
            SavingAccount savingAccount = new SavingAccount();
            savingAccount.setEmail(principal.getName());
            savingAccount.setNumber(numberGenerator.generateUniqueStringOfNumberForSavingAccount());
            savingAccount.setBalance(0.);
            savingAccountService.save(savingAccount);

            return new ResponseEntity<>(HttpStatus.OK);
        } else
            throw new RuntimeException("Saving account already exists");
    }

    @PutMapping("/replenishment/{sum}")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Double replenishment(Principal principal, @PathVariable Double sum) {

        SavingAccount savingAccount = savingAccountService.findByUserEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Saving account does not exists"));
        savingAccount.setBalance(savingAccount.getBalance() + sum);

        saveOperation(savingAccount, null, sum,
                TypeOperation.REPLENISHMENT, savingAccount.getBalance());

        return sum;
    }

    @PutMapping("/transfer/{number}/{sum}")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Double transfer(Principal principal, @PathVariable String number, @PathVariable Double sum) {
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

    @PutMapping("/pay/{sum}")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Double pay(Principal principal, @PathVariable Double sum) {

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
