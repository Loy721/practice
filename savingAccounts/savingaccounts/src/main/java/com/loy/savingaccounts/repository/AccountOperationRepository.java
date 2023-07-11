package com.loy.savingaccounts.repository;

import com.loy.savingaccounts.entity.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Integer> {

    @Query("SELECT a FROM AccountOperation a " +
            "JOIN FETCH a.savingAccountNumber s  left JOIN FETCH a.toSavingAccountNumber t  " +
            "WHERE t.number = ?1 OR s.number = ?1 " +
            "ORDER BY a.date DESC")
    List<AccountOperation> getAccountOperations(String num);


    @Query(value = "SELECT nextval('account_operation_seq')", nativeQuery = true)
    Integer getNextValSequence();
}
