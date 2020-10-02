/**
 * 
 */
package com.product.inventory.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author tvelk
 *
 */
@Data
@Entity
@Table(name = "product_inventory")
public class ProductInventory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "inventorySequence", sequenceName = "INVENTORY_SEQ", initialValue = 116)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventorySequence")
	private int id;
	
	@Column(name="quantity")
	private int availableQuantity;
	
	@Column(name="price")
	private int price;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_type")
	private String productType;
	
	@Column(name="product_variety")
	private String productVariety;
	
	@Column(name="package_type")
	private String packageType; 
}
