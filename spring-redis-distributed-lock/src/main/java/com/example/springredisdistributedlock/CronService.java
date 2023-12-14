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

    @Autowired
    public CronService(RedisDistributedLock lock) {
        this.lock = lock;
    }

    @Scheduled(fixedDelay = 8000L)
    private void cronMethod(){
        log.info("Cron job running..");
        try {
            if (lock.acquireLock("lock-key", 15000, TimeUnit.MILLISECONDS)) {
                log.info("Lock acquired. Operation started.");

                Thread.sleep(2000);

                log.info("Operation completed.");
            } else {
                log.error("Failed to acquire lock. Resource is busy.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
