package com.htc.calculator.exceptions;

import javax.xml.namespace.QName;

import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

import com.htc.calculatorservice.OperationResponse;


public class DetailSoapFaultDefinitionExceptionResolver extends SoapFaultMappingExceptionResolver{

	private static final QName CODE = new QName("operationResponseCode");
	private static final QName MESSAGE = new QName("message");

	
	@Override
	public void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
		if (ex instanceof CalculatorExceptionHandler) {
			OperationResponse opsResponse = ((CalculatorExceptionHandler) ex).getServiceResponse();
			SoapFaultDetail detail = fault.addFaultDetail();
			detail.addFaultDetailElement(CODE).addText(opsResponse.getOperationResponseCode());
			detail.addFaultDetailElement(MESSAGE).addText(opsResponse.getMessage());
		}
	}
	
}
