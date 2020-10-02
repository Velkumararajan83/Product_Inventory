/**
 * 
 */
package com.product.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author tvelk
 *
 */
@ControllerAdvice
public class ProductInventoryExceptionHandler {
	
	@ExceptionHandler(ProductInventoryException.class)
	public ResponseEntity<Object> myMessage(ProductInventoryException ex){
		
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidProductIdException.class)
	public ResponseEntity<Object> myMessage(InvalidProductIdException ex){
		
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.FOUND);
	}
	
	
}
