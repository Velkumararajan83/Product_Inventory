/**
 * 
 */
package com.product.inventory.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tvelk
 *
 */
@Configuration
@EnableCaching
public class CachingConfig {
	
	@Bean
	public CacheManager cacheManger() {
		
		return new ConcurrentMapCacheManager("product");
	}
}
