/**
 * 
 */
package com.product.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.product.inventory.entity.ProductInventory;

/**
 * @author tvelk
 *
 */
@RepositoryRestResource
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Integer> {

}
