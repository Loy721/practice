package com.loy.savingaccounts.rest;

import com.loy.savingaccounts.domain.SavingAccountOperationService;
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

    private final SavingAccountOperationService savingAccountOperationService;

    @GetMapping("/history")
    public List<AccountOperationJson> getAccountHistory(Principal principal) {
      return savingAccountOperationService.getHistory(principal);
    }

    @PostMapping
    public ResponseEntity<?> createSavingAccount(Principal principal) {
        savingAccountOperationService.createSavingAccount(principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/replenishment/{sum}")
    public Double replenishment(Principal principal, @PathVariable Double sum) {
        savingAccountOperationService.replenishment(principal,sum);
        return sum;
    }

    @PutMapping("/transfer/{number}/{sum}")
    public Double transfer(Principal principal, @PathVariable String number, @PathVariable Double sum) {
        savingAccountOperationService.transfer(principal, number, sum);
        return sum;
    }

    @PutMapping("/pay/{sum}")
    public Double pay(Principal principal, @PathVariable Double sum) {
        savingAccountOperationService.pay(principal, sum);
        return sum;
    }
}
