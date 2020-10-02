/**
 * 
 */
package com.product.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.product.inventory.entity.ProductInventory;
import com.product.inventory.repository.ProductInventoryRepository;

/**
 * @author tvelk
 *
 */
@Component
public class ProductInventoryServiceImpl implements ProductInventoryService {

	@Autowired
	ProductInventoryRepository productInventoryRepository;
	
	@Override
	public ProductInventory addProductInventory(ProductInventory productInventory) {
		
		return productInventoryRepository.save(productInventory);
	}
	
	@Override
	public boolean deleteProductInventory(int productId) {
		
		boolean hasDeleted = false;
		ProductInventory productInventory = getProductInventory(productId);
		if(productInventory != null && productInventory.getId() > 0) {
			productInventoryRepository.delete(productInventory);
			hasDeleted = true;
		}
		
		return hasDeleted;
	}
	
	@Override
	public ProductInventory saveProductInventory(ProductInventory productInventory) {
		
		return productInventoryRepository.save(productInventory);
	}
	
	@Override
	@Cacheable(value="product", key = "#productId")
	public ProductInventory getProductInventory(int productId) {
		
		return productInventoryRepository.getOne(productId);
	}

	@Override
	public List<ProductInventory> getAllProductsInventory() {

		return productInventoryRepository.findAll();
	}
	
	@Override
	public ProductInventory updateProductInventory(ProductInventory productInventory) {
		
		return productInventoryRepository.save(productInventory);
	}
}
