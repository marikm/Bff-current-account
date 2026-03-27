package com.banco.bff_mobile.messaging;

import com.banco.bff_mobile.dto.TransactionEventDTO;
import com.banco.bff_mobile.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionConsumer {
    private final AccountService accountService;
    private final ObjectMapper objectMapper;

    public TransactionConsumer(AccountService accountService, ObjectMapper objectMapper) {
        this.accountService = accountService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "transaction-events", groupId = "bff-mobile-group")
    public void consume(String message) {
        try {
            TransactionEventDTO event = objectMapper.readValue(message, TransactionEventDTO.class);
            System.out.println("[KAFKA] Message received: " + message);

            accountService.processNewTransaction(event);
            System.out.println("[KAFKA] Transaction processed, amount updated, and record saved to the database");

        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        }
    }

}
