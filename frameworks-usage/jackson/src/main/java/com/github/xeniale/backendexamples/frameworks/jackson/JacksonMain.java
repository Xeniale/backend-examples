package com.github.xeniale.backendexamples.frameworks.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xeniale.backendexamples.frameworks.jackson.mixin.ConfigMixIn;
import com.github.xeniale.backendexamples.frameworks.jackson.model.Config;

import java.io.StringWriter;
import java.util.ArrayList;
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
        List<Config> configs = new ArrayList<>();
        configs.add(config0);
        configs.add(config1);
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Config.class, ConfigMixIn.class);
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, configs);
            result = writer.toString();
            System.out.println(result);
      //      Config cfg = mapper.readValue(result, Config.class);
        //    System.out.print(cfg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

//        ClassLoader classLoader = new ClassLoader() {
//            @Override
//            public Class<?> loadClass(String name) throws ClassNotFoundException {
//                Bundle bundle = FrameworkUtil.getBundle(MetamodelRestService.class);
//                bundle.loadClass(name);
//                return bundle.loadClass(name);
//            }
//        };
//
//        ObjectMapper mapper = new ObjectMapper();
//        Thread.currentThread().setContextClassLoader(classLoader);
    }

}
