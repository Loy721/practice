package com.loy.savingaccounts.util;

import com.loy.savingaccounts.service.CardService;
import com.loy.savingaccounts.service.SavingAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class NumberGeneratorUtil {

    private final SavingAccountService savingAccountService;

    private final CardService cardService;

    private Random random = new Random();

    private String generateStringOfNumber(int length) {
        char[] string = new char[length];
        for (int i = 0; i < length; i++) {
            string[i] = (char) (random.nextInt( Character.getNumericValue('9'))+'0');
        }
        return new String(string);
    }


    public String generateUniqueStringOfNumberForSavingAccount() {
        String str;
        do {
            str = generateStringOfNumber(20);
        } while (savingAccountService.existsByNumber(str));

        return str;
    }

    public String generateUniqueStringOfNumberForCard() {
        String str;
        do {
            str = generateStringOfNumber(16);
        } while (cardService.existsByNumber(str));

        return str;
    }

    public Integer generateCvcForCard(){
        return random.nextInt(900)+100;
    }
}
