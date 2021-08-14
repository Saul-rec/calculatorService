package com.htc.calculator.exceptions;

import com.htc.calculatorservice.OperationResponse;

public class CalculatorExceptionHandler extends RuntimeException{


	private static final long serialVersionUID = 1L;
	private OperationResponse serviceResponse;
	
	public CalculatorExceptionHandler(String message, OperationResponse serviceResponse) {
		super(message);
		this.serviceResponse = serviceResponse;
	}
	
	public CalculatorExceptionHandler(String message, Throwable e, OperationResponse serviceResponse) {
		super(message,e);
		this.serviceResponse = serviceResponse;
	}
	public OperationResponse getServiceResponse() {
		return serviceResponse;
	}
	
	public void setServiResponse(OperationResponse serviceResponse) {
		this.serviceResponse = serviceResponse;
	}
	

}
