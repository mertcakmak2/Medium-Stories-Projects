package com.example.pessimisticlock.controller;

import com.example.pessimisticlock.service.BalanceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/balances")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping
    public String incrementBalance() {
        balanceService.incrementBalance();
        return "balance incremented.";
    }
}
