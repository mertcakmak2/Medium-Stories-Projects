package com.example.pessimisticlock.service;

import com.example.pessimisticlock.domain.Balance;
import com.example.pessimisticlock.repository.BalanceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class BalanceService {

    private final BalanceRepository balanceRepository;

    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Transactional
    public void incrementBalance(){
        Balance balance = balanceRepository.findBalanceByOwner("user-1")
                .orElseThrow(() -> new EntityNotFoundException("Balance not found"));

        log.info("balance incrementing..");

        balance.setBalance(balance.getBalance() + 1);
        balanceRepository.save(balance);
    }
}
