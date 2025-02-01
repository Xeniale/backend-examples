package com.github.xeniale.backendexamples;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import BookShelfServiceService.wsdl.GetBook;
import BookShelfServiceService.wsdl.GetBookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;

public class SoapClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(SoapClient.class);

    public void getBook(String name) throws Exception {

        GetBook request = new GetBook();
        request.setArg0(name);

        log.info("Requesting book with nane" + name);
//        String req = "<soapenv:Envelope \n" +
//                "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" \n" +
//                "xmlns:wsd=\"http://wsdlserver.bastida.vimpelcom.ru/\">\n" +
//                "   <soapenv:Header/>\n" +
//                "   <soapenv:Body>\n" +
//                "      <wsd:getBook>\n" +
//                "         <arg0>book_name</arg0>\n" +
//                "      </wsd:getBook>\n" +
//                "   </soapenv:Body>\n" +
//                "</soapenv:Envelope>";

        String req = "<?xml version=\"1.0\"?><getBook xmlns=\"http://wsdlserver.bastida.vimpelcom.ru/\"> \n" +
                "         <arg0>book_name</arg0>\n" +
                "      </getBook>\n";

        InputStream stream = new ByteArrayInputStream(req.getBytes(StandardCharsets.UTF_8));
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(stream);
        XMLOutputFactory xmlof = XMLOutputFactory.newInstance();

        StringWriter stringWriter = new StringWriter();

        XMLStreamWriter xmlw = xmlof.createXMLStreamWriter(stringWriter);
        StreamSource src = new StreamSource(stream);

        StAXSource stAXSource = new StAXSource(xmlr);

//        GetBook getBook = new GetBook();
//        getBook.setArg0(name);
//        GetBookResponse response = (GetBookResponse) getWebServiceTemplate().marshalSendAndReceive(getBook, new SoapActionCallback(""));
////
//        logResponse(response);
        StAXResult result = new StAXResult(xmlw);

        long t1 = System.nanoTime();

        getWebServiceTemplate().sendSourceAndReceiveToResult(stAXSource, new SoapActionCallback(""), result);

        long t2 = System.nanoTime();
        log.info("request time: " + (t2 - t1)/1000000);
        log.info(stringWriter.toString());

//        return response;
    }

    public void logResponse(GetBookResponse response) {
        log.info("Response: " + response.getReturn());
    }

}
