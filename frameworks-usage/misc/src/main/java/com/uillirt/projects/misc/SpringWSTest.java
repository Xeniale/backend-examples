package com.uillirt.projects.misc;

import com.typesafe.config.Config;
import com.uillirt.projects.misc.server.BookShelfService;
import com.uillirt.projects.misc.server.BookShelfServiceImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringWSTest {

    private static final Logger LOG = LoggerFactory.getLogger(SpringWSTest.class);

    public static void main(String[] args) {
        ConfigurableSpringLauncher.State launched = new ConfigurableSpringLauncher()
                .withArgs(args)
                .withSpringXml("ru/vimpelcom/bastida/springws-test/spring.xml")
                .launch("SpringWSTest");

        startServer(launched.config.getConfig("web-server"));

    }

    public static void startServer(Config config) {
        String address = String.format("http://%s:%s/", config.getString("interface"), config.getString("port"));

        //configure/start cxf
        BookShelfServiceImpl service = new BookShelfServiceImpl();
        JaxWsServerFactoryBean jaxWsServerFactoryBean = new JaxWsServerFactoryBean();
        jaxWsServerFactoryBean.setServiceClass(BookShelfService.class);
        jaxWsServerFactoryBean.setServiceBean(service);
        jaxWsServerFactoryBean.setAddress(address);
        jaxWsServerFactoryBean.create().start();

        LOG.info("WSDL server started at " + address);
    }
}
