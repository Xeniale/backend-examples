package com.uillirt.projects.frameworks.spring.service.rest.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//       System.out.println(Util.computeExpireDate(LocalDateTime.now(), Duration.ofDays(10)));
        Map<String, String > tokens = new HashMap<String, String>() {{
            put("2015-11-02T18:00:00.123", "2015-11-02T18:00:00.123");
            put("2015-11-03T18:00:00.123", "2015-11-03T18:00:00.123");
            put("2015-11-03T13:54:00.123", "2015-11-03T13:54:00.123");
            put("2015-11-07T10:00:00.123", "2015-11-07T10:00:00.123");
        }};
        Util.removeExpiredTokens(LocalDateTime.now(), tokens);
        System.out.println(tokens);



    }

}
