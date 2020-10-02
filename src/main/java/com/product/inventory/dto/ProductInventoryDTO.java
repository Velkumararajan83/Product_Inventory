/**
 * 
 */
package com.product.inventory.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author tvelk
 *
 */
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductInventoryDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private int availableQuantity;
	
	private int price;
	
	private String productName;
	
	private String productType;
	
	private String productVariety;
	
	private String packageType; 
}
