/**
 * 
 */
package com.product.inventory.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.inventory.converter.ProductInventoryConverter;
import com.product.inventory.dto.ProductInventoryDTO;
import com.product.inventory.exception.InvalidProductIdException;
import com.product.inventory.exception.ProductInventoryException;
import com.product.inventory.service.ProductInventoryService;

/**
 * @author tvelk
 *
 */
@RestController
@RequestMapping("/inventory")
@PropertySource(ignoreResourceNotFound = true, value="classpath:banned-products.properties")
public class ProductInventoryController {

	@Autowired
	ProductInventoryService productInventoryService;
	
	@Value("${product.type}")
	String bannedProduct;
	
	private final String STRING_ONE = "1";
	
	@GetMapping(path = "/products",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<ProductInventoryDTO> getAllProductsFromInventory() {
		
		return ProductInventoryConverter.productInventoryDTOList(productInventoryService.getAllProductsInventory());
	}
	
	
	@GetMapping(path = "/products/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ProductInventoryDTO getAProductFromInventory(@PathVariable String id) throws InvalidProductIdException {
		
		if(STRING_ONE.equals(id)) {
			throw new InvalidProductIdException();
		}
		return ProductInventoryConverter.productInventoryDTOMapper(productInventoryService.getProductInventory(Integer.parseInt(id)));
	}
	
	@PostMapping(path = "/products",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ProductInventoryDTO addAProductToInventory(@RequestBody ProductInventoryDTO productInventoryDTO) throws ProductInventoryException {
		
		ProductInventoryDTO addedProduct = null;
		if(bannedProduct.equalsIgnoreCase(productInventoryDTO.getProductType())) {
			throw new ProductInventoryException();
		}else {
			addedProduct = ProductInventoryConverter.productInventoryDTOMapper(productInventoryService.addProductInventory(ProductInventoryConverter.productInventoryMapper(productInventoryDTO)));
		}
		return addedProduct;
	}
	
	@PutMapping(path = "/products",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ProductInventoryDTO updateProductToInventory(@RequestBody ProductInventoryDTO productInventoryDTO) {
		
		return ProductInventoryConverter.productInventoryDTOMapper(productInventoryService.saveProductInventory(ProductInventoryConverter.productInventoryMapper(productInventoryDTO)));
	}
	
	@DeleteMapping(path = "/products/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> deleteAProductFromInventory(@PathVariable String id) {
		
		ResponseEntity<String> response = null;
		try {
			if(productInventoryService.deleteProductInventory(Integer.parseInt(id))) {
				response = new ResponseEntity<String>("The Product has been deleted", HttpStatus.OK);
			}else {
				response = new ResponseEntity<String>("The requested Product is not available in Inventory", HttpStatus.NO_CONTENT);
			}
		}catch(Exception ex) {
			response = new ResponseEntity<String>("The requested Product is not available in Inventory", HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping(path = "/products/{id}/price",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ProductInventoryDTO getPriceOfAProduct(@PathVariable String id, HttpServletResponse httpResponse) {
		httpResponse.setHeader("cache-control", "no-cache, no-store, must-revalidate, max-age=1800");
		return ProductInventoryConverter.productInventoryDTOMapper(productInventoryService.getProductInventory(Integer.parseInt(id)));
	}
}
