/**
 * 
 */
package com.product.inventory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author tvelk
 *
 */
public class ProductInventoryTest extends InventoryApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetProduct() throws Exception {
		mockMvc.perform(get("/inventory/products/102")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.productName").value("Lacoste")).andExpect(jsonPath("$.packageType").value("Bottle"));
	}
	
	@Test
	public void testGetAllProducts() throws Exception {
		mockMvc.perform(get("/inventory/products")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}
	
	@Test
	public void testAddAProduct() throws Exception {
		String product = "{\r\n" + 
				"    \"availableQuantity\": 155,\r\n" + 
				"    \"price\": 202,\r\n" + 
				"    \"productName\": \"Boost\",\r\n" + 
				"    \"productType\": \"Powder\",\r\n" + 
				"    \"productVariety\": \"Food\",\r\n" + 
				"    \"packageType\": \"Refill Pack\"\r\n" + 
				"}";
		mockMvc.perform(post("/inventory/products").content(product).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.packageType").value("Refill Pack")).andExpect(jsonPath("$.id").isNotEmpty());
	}
	
	@Test
	public void testDeleteAProduct() throws Exception {
		mockMvc.perform(delete("/inventory/products/101")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().string("The Product has been deleted"));
	}
	
	
	@Test
	public void testUpdateAProduct() throws Exception {
		String product = "{\r\n" + 
				"        \"id\": 103,\r\n" + 
				"        \"availableQuantity\": 155,\r\n" + 
				"        \"price\": 195,\r\n" + 
				"        \"productName\": \"Surf\",\r\n" + 
				"        \"productType\": \"Powder\",\r\n" + 
				"        \"productVariety\": \"Washing\",\r\n" + 
				"        \"packageType\": \"Plastic Container\"\r\n" + 
				"    },";
		mockMvc.perform(post("/inventory/products").content(product).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.price").value("195"));
	}
	
	@Test
	public void testAddBannedProduct() throws Exception {
		String product = "{\r\n" + 
				"    \"availableQuantity\": 155,\r\n" + 
				"    \"price\": 2000,\r\n" + 
				"    \"productName\": \"Columbian Cigar\",\r\n" + 
				"    \"productType\": \"cigar\",\r\n" + 
				"    \"productVariety\": \"Hazard\",\r\n" + 
				"    \"packageType\": \"Box\"\r\n" + 
				"}";
		mockMvc.perform(post("/inventory/products").content(product).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andExpect(content().string("This product type is banned"));
	}
}
