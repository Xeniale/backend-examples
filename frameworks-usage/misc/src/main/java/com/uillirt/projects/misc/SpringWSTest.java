package com.uillirt.projects.misc;

public class SpringWSTest {

    public static void main(String[] args) {
        ConfigurableSpringLauncher.State launched = new ConfigurableSpringLauncher()
                .withArgs(args)
                .withSpringXml("ru/vimpelcom/bastida/springws-test/spring.xml")
                .launch("SpringWSTest");


    }
}
