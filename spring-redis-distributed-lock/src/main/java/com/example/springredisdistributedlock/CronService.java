package com.example.springredisdistributedlock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class CronService {

    private final RedisDistributedLock lock;
    private final String LOCK_KEY = "lock-key";

    @Autowired
    public CronService(RedisDistributedLock lock) {
        this.lock = lock;
    }

    @Scheduled(fixedDelay = 15000L)
    private void cronMethod() throws InterruptedException {
        log.info("Cron job running..");

        if (lock.acquireLock(this.LOCK_KEY, 15000, TimeUnit.MILLISECONDS)) {
            log.info("Lock acquired. Operation started.");

            Thread.sleep(200);

            log.info("Operation completed.\n*******");
        } else {
            log.error("Failed to acquire lock. Resource is busy.\n*******");
        }

    }
}
