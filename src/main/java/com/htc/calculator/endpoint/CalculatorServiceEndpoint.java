package com.htc.calculator.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.htc.calculator.exceptions.CalculatorExceptionHandler;
import com.htc.calculator.operations.CalculatorOperationsService;
import com.htc.calculatorservice.AddRequest;
import com.htc.calculatorservice.AddResponse;
import com.htc.calculatorservice.DivideRequest;
import com.htc.calculatorservice.DivideResponse;
import com.htc.calculatorservice.MultiplyRequest;
import com.htc.calculatorservice.MultiplyResponse;
import com.htc.calculatorservice.OperationResponse;
import com.htc.calculatorservice.SubstractRequest;
import com.htc.calculatorservice.SubstractResponse;

@Endpoint
public class CalculatorServiceEndpoint {

	private static final String NAMESPACE_URI = "http://www.htc.com/calculatorservice";
	private CalculatorOperationsService cOperations;
	private OperationResponse opsResponse = new OperationResponse();

	@Autowired
	public CalculatorServiceEndpoint(CalculatorOperationsService cOperations) {
		this.cOperations = cOperations;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddRequest")
	@ResponsePayload
	public AddResponse addResponse(@RequestPayload AddRequest request) {
		AddResponse response = new AddResponse();
		if (request.getFirstNumber() == 0 || request.getSecondNumber() == 0) {
			String errorMessage = "MISSING DATA";
			opsResponse.setOperationResponseCode("400 Bad Request");
			opsResponse.setMessage("Any number provided to perform an operation");
			throw new CalculatorExceptionHandler(errorMessage, opsResponse);
		}
		response.setAddResult(cOperations.add(request.getFirstNumber(), request.getSecondNumber()));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "SubstractRequest")
	@ResponsePayload
	public SubstractResponse subtractResponse(@RequestPayload SubstractRequest request) {
		SubstractResponse response = new SubstractResponse();
		if (request.getFirstNumber() == 0 || request.getSecondNumber() == 0) {
			String errorMessage = "MISSING DATA";
			opsResponse.setOperationResponseCode("400 Bad Request");
			opsResponse.setMessage("Any number provided to perform an operation");
			throw new CalculatorExceptionHandler(errorMessage, opsResponse);
		}
		response.setSubstractResult(cOperations.substract(request.getFirstNumber(), request.getSecondNumber()));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "DivideRequest")
	@ResponsePayload
	public DivideResponse divideResponse(@RequestPayload DivideRequest request) throws CalculatorExceptionHandler {
		DivideResponse response = new DivideResponse();
		if (request.getFirstNumber() == 0 || request.getSecondNumber() == 0) {
			String errorMessage = "MISSING DATA";
			opsResponse.setOperationResponseCode("400 Bad Request");
			opsResponse.setMessage("Any number provided to perform an operation");
			throw new CalculatorExceptionHandler(errorMessage, opsResponse);
		}
		response.setDivideResponse(cOperations.divide(request.getFirstNumber(), request.getSecondNumber()));
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "MultiplyRequest")
	@ResponsePayload
	public MultiplyResponse multiplyResponse(@RequestPayload MultiplyRequest request) {
		MultiplyResponse response = new MultiplyResponse();
		if (request.getFirstNumber() == 0 || request.getSecondNumber() == 0) {
			String errorMessage = "MISSING DATA";
			opsResponse.setOperationResponseCode("400 Bad Request");
			opsResponse.setMessage("Any number provided to perform an operation");
			throw new CalculatorExceptionHandler(errorMessage, opsResponse);
		}
		response.setMultiplyResult(cOperations.multiply(request.getFirstNumber(), request.getSecondNumber()));
		return response;
	}
}
