package com.example.redisom.config;


import org.springframework.data.redis.core.convert.KeyspaceConfiguration;

public class MyKeyspaceConfiguration extends KeyspaceConfiguration {


    @Override
    public boolean hasSettingsFor(Class<?> type) {
        return true;
    }

    @Override
    public KeyspaceSettings getKeyspaceSettings(Class<?> type) {

        KeyspaceSettings keyspaceSettings = new KeyspaceSettings(type, "my-keyspace");
        keyspaceSettings.setTimeToLive(300L);

        return keyspaceSettings;
    }
}
