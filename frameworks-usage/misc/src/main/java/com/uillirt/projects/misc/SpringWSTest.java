package com.uillirt.projects.misc;

import com.typesafe.config.Config;
import com.uillirt.projects.SoapClient;
import com.uillirt.projects.misc.server.BookShelfService;
import com.uillirt.projects.misc.server.BookShelfServiceImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringWSTest {

    public static void main(String[] args) throws Exception {
        ConfigurableSpringLauncher.State launched = new ConfigurableSpringLauncher()
                .withArgs(args)
                .withSpringXml("ru/vimpelcom/bastida/springws-test/spring.xml")
                .launch("SpringWSTest");

        SoapClient client = launched.springContext.getBean("soapClient", SoapClient.class);

        client.getBook("book_name");
    }

}
