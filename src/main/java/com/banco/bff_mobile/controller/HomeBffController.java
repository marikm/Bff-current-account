package com.banco.bff_mobile.controller;


import com.banco.bff_mobile.dto.HomeBffResponseDTO;
import com.banco.bff_mobile.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mobile/bff")
public class HomeBffController {

    private final AccountService accountService;

    public HomeBffController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/home/{accountId}")
    public ResponseEntity<HomeBffResponseDTO> getHomeData(@PathVariable Integer accountId) {
        HomeBffResponseDTO response = accountService.getHomeData(accountId);
        return ResponseEntity.ok(response);
    }

}
