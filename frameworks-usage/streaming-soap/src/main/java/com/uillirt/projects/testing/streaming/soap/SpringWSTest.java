package com.uillirt.projects.testing.streaming.soap;

public class SpringWSTest {

    public static void main(String[] args) throws Exception {
        ConfigurableSpringLauncher.State launched = new ConfigurableSpringLauncher()
                .withArgs(args)
                .withSpringXml("com/uillirt/projects/testing/streaming/soap/service/spring.xml")
                .launch("SpringWSTest");

//        SoapClient client = launched.springContext.getBean("soapClient", SoapClient.class);
//
//        client.getBook("book_name");
//        CxfSoapClient cxfSoapClient = new CxfSoapClient();
//
//        cxfSoapClient.init();
//
//        cxfSoapClient.getBook("book_name");

        int i = 0;
    }

}
