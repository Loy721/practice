package com.loy.savingaccounts.repository;

import com.loy.savingaccounts.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
    Boolean existsByNumber(String num);
}
