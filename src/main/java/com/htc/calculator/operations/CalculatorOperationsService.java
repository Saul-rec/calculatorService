package com.htc.calculator.operations;

import org.springframework.stereotype.Service;

import com.htc.calculator.exceptions.CalculatorExceptionHandler;
import com.htc.calculatorservice.OperationResponse;

@Service
public class CalculatorOperationsService {

	private OperationResponse opsResponse = new OperationResponse();
	
	public int add(int firstNumber, int secondNumber) {
		simpleRequestFieldValidation(firstNumber, secondNumber);
		return firstNumber + secondNumber;
	}
	public int substract(int firstNumber, int secondNumber) {
		simpleRequestFieldValidation(firstNumber, secondNumber);
		return firstNumber - secondNumber;
	}
	public int divide(int firstNumber, int secondNumber) throws CalculatorExceptionHandler{
		simpleRequestFieldValidation(firstNumber, secondNumber);
		if (secondNumber == 0) {
			String errorMessage = "ArithmeticException";
			opsResponse.setOperationResponseCode("400:Bad Request");
			opsResponse.setMessage("Division by zero not allowed");
			
			throw new CalculatorExceptionHandler(errorMessage, opsResponse);
		}
		return  firstNumber / secondNumber;
	}
	public int multiply(int firstNumber, int secondNumber) {
		simpleRequestFieldValidation(firstNumber, secondNumber);
		return firstNumber * secondNumber;
	}
	
	public void simpleRequestFieldValidation(int number1, int number2) {
		if (number1 == 0 && number2 == 0) {
			String errorMessage = "NO VALID DATA";
			opsResponse.setOperationResponseCode("400:Bad Request");
			opsResponse.setMessage("Please type only integers numbers and grater than zero");
			throw new CalculatorExceptionHandler(errorMessage, opsResponse);
		}
	}
	
	
}
