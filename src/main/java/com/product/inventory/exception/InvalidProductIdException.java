/**
 * 
 */
package com.product.inventory.exception;

/**
 * @author tvelk
 *
 */
public class InvalidProductIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidProductIdException() {
		super("Invalid Product Id has given in the request");
	}

}
