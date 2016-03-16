package com.uillirt.projects.misc;

import BookShelfServiceService.wsdl.ObjectFactory;
import org.apache.xml.resolver.readers.SAXParserHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;

public class CxfSoapClient {

    private static final ObjectFactory of = new ObjectFactory();
    private static final Logger LOG = LoggerFactory.getLogger(CxfSoapClient.class);

    private Service bookshelfservice;
    private Dispatch<SAXSource> dispatch;

    public void init() throws Exception {
        bookshelfservice = Service.create(new URL("http://127.0.0.1:2010/?wsdl"), new QName("http://wsdlserver.bastida.vimpelcom" +
                ".ru/", "BookShelfServiceService"));
        dispatch  = bookshelfservice.createDispatch(
                new QName("http://wsdlserver.bastida.vimpelcom.ru/", "BookShelfServicePort"),
                SAXSource.class,
                Service.Mode.PAYLOAD
        );
    }

    public void getBook(String name) {
        String req = "<?xml version=\"1.0\"?><getBook xmlns=\"http://wsdlserver.bastida.vimpelcom.ru/\"> \n" +
                "         <arg0>" + name + "</arg0>\n" +
                "      </getBook>\n";

        InputStream stream = new ByteArrayInputStream(req.getBytes(StandardCharsets.UTF_8));
        SAXSource src = new SAXSource(new InputSource(stream));

        long t1 = System.nanoTime();
        dispatch.invokeAsync(src, r -> {
            try {
                long t2 = System.nanoTime();
                LOG.info("got response for: " + (t2 - t1)/1000000);
                SAXSource s = r.get();
                XMLReader reader = s.getXMLReader();
                reader.setContentHandler(new CustomHandler());
                reader.parse(s.getInputSource());
            } catch (Throwable t) {
                LOG.error("Exception ", t);
            }
        });
        long t2 = System.nanoTime();

        LOG.info("invoke async for: " + (t2 - t1)/1000000);
    }

    public class CustomHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws
                SAXException {
            LOG.info("uri: " + uri + "local name: " + localName + " qName: " + qName);
        }
    }
}
