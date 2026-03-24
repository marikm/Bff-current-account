package com.banco.bff_mobile.repository;

import com.banco.bff_mobile.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findTop5ByAccountIdOrderByTransactionDateDesc(Integer accountId);
}
