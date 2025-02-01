package com.github.xeniale.backendexamples.frameworks.spring.service.rest.impl;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Util {
    private static final DateTimeFormatter EXPIRATION_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public static Map<byte[], byte[]> userToByteArrayMap(User user) throws IllegalAccessException {
        Map<byte[], byte[]> userToByteArrayMap = new HashMap<>();
        Field[] fields = user.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(user);
            System.out.println(value);
        }
        return userToByteArrayMap;
    }

    public static LocalDateTime computeExpireDate(LocalDateTime date, Duration tokenDuration) {
        Temporal expireDate = tokenDuration.addTo(date);
        return LocalDateTime.from(expireDate);
    }

    public static void removeExpiredTokens(LocalDateTime date, Map<String, String> tokenToExpirationDateMap) {
        Iterator<Map.Entry<String, String>> iter = tokenToExpirationDateMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            LocalDateTime expireDateFromMap = LocalDateTime.from(EXPIRATION_DATE_FORMATTER.parse(entry.getValue()));
            if (expireDateFromMap.isBefore(date)) {
                iter.remove();
            }
        }
    }
}
