/**
 * 
 */
package com.product.inventory.service;

import java.util.List;

import com.product.inventory.entity.ProductInventory;

/**
 * @author tvelk
 *
 */
public interface ProductInventoryService {
	
	public ProductInventory addProductInventory(ProductInventory productInventory);
	
	public boolean deleteProductInventory(int productId);
	
	public ProductInventory getProductInventory(int productId);
	
	public List<ProductInventory> getAllProductsInventory();
	
	public ProductInventory saveProductInventory(ProductInventory productInventory);
	
	public ProductInventory updateProductInventory(ProductInventory productInventory);
	
	
}
