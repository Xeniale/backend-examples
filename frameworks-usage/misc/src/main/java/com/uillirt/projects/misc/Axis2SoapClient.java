package com.uillirt.projects.misc;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.async.AxisCallback;
import org.apache.axis2.context.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;

public class Axis2SoapClient {
    private static EndpointReference targetEPR = new EndpointReference("http://127.0.0.1:2010/");

    private static final Logger LOG = LoggerFactory.getLogger(Axis2SoapClient.class);

    public static OMElement createRequest(String name) {
//        String req = "<?xml version=\"1.0\"?><getBook xmlns=\"http://wsdlserver.bastida.vimpelcom.ru/\"> \n" +
//                "         <arg0>" + name + "</arg0>\n" +
//                "      </getBook>\n";
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://wsdlserver.bastida.vimpelcom.ru/", "tns");

        OMElement method = fac.createOMElement("getBook", omNs);
        OMElement value = fac.createOMElement("arg0", omNs);
        value.addChild(fac.createOMText(value, name));
        method.addChild(value);
        return method;
    }

    public static void main(String[] args) {
        ServiceClient sender = null;
        try {
            OMElement payload = createRequest("book_name");

            Options options = new Options();
            options.setTo(targetEPR);
            options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
            //Callback to handle the response
            AxisCallback callback = new AxisCallback() {
                public void onComplete() {
                    System.out.println("completed");
                }

                @Override
                public void onMessage(MessageContext messageContext) {
                    System.out.println("msg headers " + messageContext.getAxisMessage().getSoapHeaders());
                }

                @Override
                public void onFault(MessageContext messageContext) {
                    System.out.println("msg fault headers " + messageContext.getAxisMessage().getSoapHeaders());
                }

                public void onError(Exception e) {
                    System.out.println("errror");
                }
            };

            //Non-Blocking Invocation
            sender = new ServiceClient();
            sender.setOptions(options);
            OMElement response = sender.sendReceive(new QName("http://wsdlserver.bastida.vimpelcom.ru/", "getBook"),
                    payload);

            LOG.info("r " + response);
            Thread.sleep(1000);
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                sender.cleanup();
            } catch (AxisFault axisFault) {
                //
            }
        }

    }
}
