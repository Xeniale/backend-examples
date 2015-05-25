package com.uillirt.projects.osgi.services.test;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.ConfigurationPolicy;
import aQute.bnd.annotation.component.Deactivate;
import com.uillirt.projects.osgi.services.test.type.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Ks on 28.01.2015.
 */

@Component(
        name = TestServiceImpl.COMPONENT_NAME,
        configurationPolicy = ConfigurationPolicy.ignore,
        provide = {TestService.class},
        immediate = true
)
public class TestServiceImpl implements TestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);

    public static final String COMPONENT_NAME = "com.uillirt.projects.osgi.services.test";

    public void sayHello() {
        LOGGER.info("----------------------------");
        LOGGER.info("Hello, OSGi!");
        LOGGER.info("----------------------------");
    }

    @Activate
    public void activate(final Map<String, String> properties) throws Exception {
        LOGGER.info("TestServiceImpl component starting...");
        LOGGER.info("TestServiceImpl component started.");
    }

    @Deactivate
    public void deactivate() {
        LOGGER.info("TestServiceImpl component stopping...");
        LOGGER.info("TestServiceImpl component stopped.");
    }
}
