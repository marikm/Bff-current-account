package com.banco.bff_mobile.dto;

import java.math.BigDecimal;
import java.util.List;

public record HomeBffResponseDTO(
        String costumerName,
        String accountNumber,
        BigDecimal currentBalance,
        List<TransactionDTO> recentTransactions

        ) {
}
