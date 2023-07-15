package com.loy.savingaccounts.service.impl;

import com.loy.savingaccounts.entity.Card;
import com.loy.savingaccounts.repository.CardRepository;
import com.loy.savingaccounts.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Boolean existsByNumber(String num) {
        return cardRepository.existsByNumber(num);
    }
}
