/**
 * 
 */
package com.product.inventory.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.product.inventory.dto.ProductInventoryDTO;
import com.product.inventory.entity.ProductInventory;

/**
 * @author tvelk
 *
 */
@Component
public class ProductInventoryConverter {
	
	
	public static List<ProductInventory> productInventoryList(List<ProductInventoryDTO> productInventoryDTOList) {
		List<ProductInventory> productInventoryList = new ArrayList<ProductInventory>();
		
		for(ProductInventoryDTO productInventoryDTO : productInventoryDTOList) {
			productInventoryList.add(productInventoryMapper(productInventoryDTO));
		}
		
		return productInventoryList;
	}
	
	public static List<ProductInventoryDTO> productInventoryDTOList(List<ProductInventory> productInventoryList) {
		List<ProductInventoryDTO> productInventoryDTOList = new ArrayList<ProductInventoryDTO>();
		for(ProductInventory productInventory:productInventoryList) {
			productInventoryDTOList.add(productInventoryDTOMapper(productInventory));
		}
		return productInventoryDTOList;
	}
	
	public static ProductInventory productInventoryMapper(ProductInventoryDTO productInventoryDTO) {
		ProductInventory productInventory = new ProductInventory();
		
		productInventory.setAvailableQuantity(productInventoryDTO.getAvailableQuantity());
		productInventory.setId(productInventoryDTO.getId());
		productInventory.setPackageType(productInventoryDTO.getPackageType());
		productInventory.setPrice(productInventoryDTO.getPrice());
		productInventory.setProductName(productInventoryDTO.getProductName());
		productInventory.setProductType(productInventoryDTO.getProductType());
		productInventory.setProductVariety(productInventoryDTO.getProductVariety());
		
		return productInventory;
	}
	
	public static ProductInventoryDTO productInventoryDTOMapper(ProductInventory productInventory) {
		ProductInventoryDTO productInventoryDTO = new ProductInventoryDTO();
		
		productInventoryDTO.setAvailableQuantity(productInventory.getAvailableQuantity());
		productInventoryDTO.setId(productInventory.getId());
		productInventoryDTO.setPackageType(productInventory.getPackageType());
		productInventoryDTO.setPrice(productInventory.getPrice());
		productInventoryDTO.setProductName(productInventory.getProductName());
		productInventoryDTO.setProductType(productInventory.getProductType());
		productInventoryDTO.setProductVariety(productInventory.getProductVariety());
		
		return productInventoryDTO;
	}
	
	
}
