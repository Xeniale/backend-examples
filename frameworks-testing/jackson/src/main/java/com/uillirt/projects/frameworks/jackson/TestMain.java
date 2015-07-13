package com.uillirt.projects.frameworks.jackson;

import org.xml.sax.SAXParseException;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by kshekhovtsova on 10.07.2015.
 */
public class TestMain {

    public static final String MSG = "<Msg entitySet=\"customer\" init=\"true\" id=\"1\">\n" +
            "    <Entity name=\"cust_1\" operation=\"I\">\n" +
            "        <Fields>\n" +
            "            <Id>20</Id>\n" +
            "<Name><![CDATA[name]]></Name>"+
            "        </Fields>\n" +
            "    </Entity>\n" +
            "</Msg>";
    private static final XMLInputFactory xif = XMLInputFactory.newInstance();

    public static void main(String[] args) {
        try {
            process(MSG);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void process(String message) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader xmlStreamReader =
                factory.createXMLEventReader(new StringReader(message));
        XMLEvent event;
        while (xmlStreamReader.hasNext()) {
            event = xmlStreamReader.nextEvent();
            switch (event.getEventType()) {
                case (XMLEvent.CDATA):
                    System.out.printf("CDATA: %s\n", event.asCharacters().getData());
                    break;
                case (XMLStreamConstants.CHARACTERS):
                    System.out.printf("Chars: %s\n", event.asCharacters().getData());
                    break;
                case (XMLStreamConstants.START_ELEMENT):
                    System.out.printf("start element: %s\n", event.asStartElement().getName().getLocalPart());
                    Attribute attribute = event.asStartElement().getAttributeByName(new QName("name"));
                    if (attribute != null) {
                        System.out.printf(attribute.getValue());
                    }
                    break;
                default:
                    break;

            }
        }

    }
}
