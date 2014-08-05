package com.unei.handler;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;


public class CheckHandler implements SOAPHandler<SOAPMessageContext>{
	
	public boolean handleMessage(SOAPMessageContext context) {
		System.out.println("Server:handleMessage....");
		Boolean isResponse=(Boolean)context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if(!isResponse){
			System.out.println("request....");
			
			try {
				SOAPMessage soapMsg=context.getMessage();
				SOAPEnvelope soapEnv=soapMsg.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader=soapEnv.getHeader();
				//if no header,add one,throw exception
				if(soapHeader==null){
					soapHeader=soapEnv.addHeader();
					generateSoapErrMessage(soapMsg, "No SOAP header.");
				}
				
				//Get 
				Iterator it=soapHeader.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
				
				if(it==null||!it.hasNext()){
					generateSoapErrMessage(soapMsg, "No header block for next");
				}
				
				Node a=(Node)it.next();
				System.out.println(a.getValue());
				
			} catch (SOAPException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("response....");
		}
		return true;
	}

	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	public void close(MessageContext context) {
		
	}

	public Set<QName> getHeaders() {
		return null;
	}
	
	private void generateSoapErrMessage(SOAPMessage msg,String reason){
		try {
			SOAPBody soapBody=msg.getSOAPPart().getEnvelope().getBody();
			SOAPFault soapFault=soapBody.addFault();
			soapFault.setFaultString(reason);
			throw new SOAPFaultException(soapFault);
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		
	}
}
