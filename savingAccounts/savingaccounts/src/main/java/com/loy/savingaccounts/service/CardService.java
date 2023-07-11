package com.loy.savingaccounts.service;

import com.loy.savingaccounts.entity.Card;

public interface CardService {
    Card save(Card card);

    Boolean existsByNumber(String num);
}
