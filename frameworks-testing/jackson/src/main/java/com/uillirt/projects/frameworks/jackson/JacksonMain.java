package com.uillirt.projects.frameworks.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uillirt.projects.frameworks.jackson.model.Config;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by kshekhovtsova on 19.05.2015.
 */
public class JacksonMain {

    public static void main(String[] args) {
        String result;
        Config config3 = new Config();
        config3.setId(3);
        config3.setName("config3");
        config3.setConfig(null);
        Config config2 = new Config();
        config2.setId(2);
        config2.setName("config2");
        config2.setConfig(config3);
        Config config1 = new Config();
        config1.setId(1);
        config1.setName("config1");
        config1.setConfig(config2);
        Config config0 = new Config();
        config0.setId(0);
        config0.setName("config0");
        config0.setConfig(config1);
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, config0);
            result = writer.toString();
            Config cfg = mapper.readValue(result, Config.class);
            System.out.print(cfg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

}
