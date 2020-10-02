/**
 * 
 */
package com.product.inventory.exception;

/**
 * @author tvelk
 *
 */
public class ProductInventoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProductInventoryException() {
		super("This product type is banned");
	}
}
