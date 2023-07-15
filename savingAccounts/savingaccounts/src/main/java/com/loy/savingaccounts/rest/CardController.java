package com.loy.savingaccounts.rest;

import com.loy.savingaccounts.entity.Card;
import com.loy.savingaccounts.entity.SavingAccount;
import com.loy.savingaccounts.service.CardService;
import com.loy.savingaccounts.service.SavingAccountService;
import com.loy.savingaccounts.util.NumberGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    private final SavingAccountService savingAccountService;

    private final NumberGeneratorUtil numberGenerator;

    @PostMapping
    public ResponseEntity<?> create() {
        //TODO: find Id from Authentication
        String id = "d";
        SavingAccount savingAccount = savingAccountService.findByUserEmail(id).orElseThrow();//TODO
        Card card = new Card(numberGenerator.generateUniqueStringOfNumberForCard(),
                LocalDate.now().plusYears(5), numberGenerator.generateCvcForCard(), savingAccount);
        cardService.save(card);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO пополнение ...

}
