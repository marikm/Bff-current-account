package com.banco.bff_mobile.dto;

import java.math.BigDecimal;

public record TransactionEventDTO(
        Integer accountId,
        BigDecimal amount,
        String description
) {
}
