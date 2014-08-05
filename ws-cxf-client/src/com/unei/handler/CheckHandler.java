package com.unei.handler;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class CheckHandler implements SOAPHandler<SOAPMessageContext>{

	public void close(MessageContext arg0) {		
	}

	public boolean handleFault(SOAPMessageContext arg0) {
		return false;
	}

	public boolean handleMessage(SOAPMessageContext context) {
		Boolean isRequest=(Boolean)context.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY);
		
		if(isRequest){
			System.out.println("request...");
			SOAPMessage soapMsg=context.getMessage();
			SOAPEnvelope soapEnv;
			try {
				soapEnv = soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader=soapEnv.getHeader();
				
				if(soapHeader==null){
					soapHeader=soapEnv.addHeader();
				}
				
				String mac=getMACAddress();
				
				QName qname=new QName("http://localhost","macAddress");
				SOAPHeaderElement soapHeaderElement=soapHeader.addHeaderElement(qname);
				
				soapHeaderElement.setActor(SOAPConstants.URI_SOAP_ACTOR_NEXT);
				soapHeaderElement.addTextNode(mac);
				soapMsg.saveChanges();
				
				soapMsg.writeTo(System.out);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else{
			System.out.println("response...");
		}
		return true;
	}

	public Set<QName> getHeaders() {
		return null;
	}

	private String getMACAddress() throws Exception{

		StringBuilder sb=new StringBuilder();
		InetAddress ip=InetAddress.getLocalHost();
		System.out.println("Current IP address:"+ip.getHostAddress());
		NetworkInterface network=NetworkInterface.getByInetAddress(ip);
		return ip.toString();
		
		/*byte[] mac=network.getHardwareAddress();
		System.out.println("Current MAC address:");
		for(int i=0;i<mac.length;i++){
			sb.append(String.format("%02X%s",mac[i],(i<mac.length-1)?"-":""));
		}
		
		System.out.println(sb.toString());
		return sb.toString();*/
	}
}
