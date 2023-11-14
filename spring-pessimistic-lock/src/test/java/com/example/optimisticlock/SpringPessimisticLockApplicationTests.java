package com.example.optimisticlock;

import com.example.pessimisticlock.service.BalanceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringPessimisticLockApplicationTests {

    private final BalanceService balanceService;

    @Autowired
    SpringPessimisticLockApplicationTests(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @Test
    void contextLoads() throws InterruptedException {

        for (int i = 0; i < 50; i++) {
            Thread thread = new Thread(() ->{
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                balanceService.incrementBalance();
            });
            thread.start();
        }

        Thread.sleep(7000);

    }

}
