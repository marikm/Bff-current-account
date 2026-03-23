package com.banco.bff_mobile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Tester {
    @GetMapping("/status")
    public String status() {
        return "Teste";
    }
}
