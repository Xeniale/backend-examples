package com.github.xeniale.backendexamples.frameworks.spring.service.rest.impl;

import java.util.Date;
import java.util.HashMap;

public class Service {
    public void run() {
        User user = new User(1L, "ksenia", "123", 1, new Date(), new HashMap<String, Date>() {{
            put("1:2015-11-02T17:00:01.123", new Date());
        }});
        try {
            System.out.print(Util.userToByteArrayMap(user));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
