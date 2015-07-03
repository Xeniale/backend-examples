package com.uillirt.projects.frameworks.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import com.uillirt.projects.frameworks.jaxb.config.input.Configuration;

/**
 * Created by kshekhovtsova on 03.07.2015.
 */
public class ConfigFactory {
    private Unmarshaller jaxbUnmarshaller;

    public ConfigFactory() throws Exception {
        this.jaxbUnmarshaller = JAXBContext.newInstance(Configuration.class).createUnmarshaller();
    }

    public Configuration createInputConfig(String filePath) throws Exception {
        try {
            File file = new File(filePath);
            Object cfg = jaxbUnmarshaller.unmarshal(new BufferedInputStream(new FileInputStream(file)));
            Configuration inputConfiguration = (Configuration) cfg;
            return inputConfiguration;
        }
        catch (FileNotFoundException | JAXBException e) {
            throw new Exception("Can't create input config",e);
        }

    }
}
