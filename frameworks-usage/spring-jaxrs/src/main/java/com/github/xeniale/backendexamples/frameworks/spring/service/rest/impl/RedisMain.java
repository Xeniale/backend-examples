package com.github.xeniale.backendexamples.frameworks.spring.service.rest.impl;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnectionPool;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.sync.RedisCommands;

import java.util.concurrent.TimeUnit;

public class RedisMain {
    public static void main(String[] args) {
        RedisURI uri = new RedisURI();
        uri.setHost("192.168.6.134");
        uri.setTimeout(1000);
        uri.setUnit(TimeUnit.MILLISECONDS);
        RedisClient client = RedisClient.create(uri);
        RedisConnectionPool<RedisCommands<String, String>> pool = client.pool();


    }

    public static void select(RedisConnectionPool<RedisCommands<String, String>> pool) {
        try(RedisCommands<String, String> commands = pool.allocateConnection()) {
            commands.multi();
            commands.exec();
        }
    }

}
