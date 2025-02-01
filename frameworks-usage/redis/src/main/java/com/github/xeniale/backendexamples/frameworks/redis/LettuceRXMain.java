package com.github.xeniale.backendexamples.frameworks.redis;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.api.async.RedisHashAsyncCommands;
import com.lambdaworks.redis.api.rx.RedisHashReactiveCommands;
import com.lambdaworks.redis.api.rx.RedisStringReactiveCommands;

import java.util.concurrent.TimeUnit;

/**
 * Created by kshekhovtsova on 07.11.2015.
 */
public class LettuceRXMain {

    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create(new RedisURI("", 6739, 3L, TimeUnit.MILLISECONDS));
        RedisHashReactiveCommands<String, String> commands = redisClient.connect().reactive();
    }

}
