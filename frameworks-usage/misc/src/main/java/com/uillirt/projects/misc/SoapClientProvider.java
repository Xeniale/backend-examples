package com.uillirt.projects.misc;

import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stax.StAXSource;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

@WebServiceProvider(
        portName = "SimpleClientPort",
        serviceName = "SimpleClientService",
        targetNamespace = "http://jaxws.webservices.examples/",
        wsdlLocation = "SimpleClientService.wsdl"
)
@ServiceMode(value = Service.Mode.PAYLOAD)
public class SoapClientProvider implements Provider<SAXSource> {

    @Override
    public SAXSource invoke(SAXSource request) {
        return null;
    }

}
