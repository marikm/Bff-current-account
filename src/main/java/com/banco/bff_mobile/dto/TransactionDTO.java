package com.banco.bff_mobile.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(
        String description,
        BigDecimal amount,
        String type,
        LocalDateTime transactionDate
) {
}
