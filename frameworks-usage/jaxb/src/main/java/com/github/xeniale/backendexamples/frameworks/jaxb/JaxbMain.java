package com.github.xeniale.backendexamples.frameworks.jaxb;

import com.github.xeniale.backendexamples.frameworks.jaxb.config.input.Configuration;

/**
 * Created by kshekhovtsova on 03.07.2015.
 */
public class JaxbMain {
    public static void main(String[] args) {
        try {
            ConfigFactory factory = new ConfigFactory();
            Configuration config = factory.createInputConfig("C:\\Users\\kshekhovtsova\\Desktop\\bridges_configs\\input\\entities\\IN.BRIDGES.CUSTOMER.xml");
            System.out.println(config);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
