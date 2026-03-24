package com.banco.bff_mobile.service;

import com.banco.bff_mobile.dto.HomeBffResponseDTO;
import com.banco.bff_mobile.dto.TransactionDTO;
import com.banco.bff_mobile.entity.Account;
import com.banco.bff_mobile.entity.Transaction;
import com.banco.bff_mobile.repository.AccountRepository;
import com.banco.bff_mobile.repository.TransactionRepository;

import java.util.List;

public class AccountService {

    private final AccountRepository accountRepository;
    private  final TransactionRepository transactionRepository;
    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public HomeBffResponseDTO getHomeData(Integer accountId) {
        // 1. search the account
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found for ID:" + accountId));
        // 2. search last 5 transactions
        List<Transaction> recentTransactions = transactionRepository.findTop5ByAccountIdOrderByTransactionDateDesc(accountId);
        // 3. Convert List<Transaction> -> List<TransactioinDTO>
        List<TransactionDTO> transactionDTOS = recentTransactions.stream().map(t -> new TransactionDTO(
                t.getDescription(),
                t.getAmount(),
                t.getType(),
                t.getTransactionDate()
        )).toList();

        // 4. DTO to BFF
        return new HomeBffResponseDTO(
                account.getCustomerName(),
                account.getAccountNumber(),
                account.getCurrentBalance(),
                transactionDTOS
        );
    }
}
