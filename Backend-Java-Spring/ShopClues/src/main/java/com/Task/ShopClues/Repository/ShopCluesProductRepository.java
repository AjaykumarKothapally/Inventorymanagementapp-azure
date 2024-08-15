package com.Task.ShopClues.Repository;

import com.Task.ShopClues.Entity.Products;
import org.springframework.stereotype.Repository;
import com.azure.spring.data.cosmos.repository.CosmosRepository;

import java.util.Optional;

@Repository
public interface ShopCluesProductRepository extends CosmosRepository<Products, String> {
	Optional<Products> findByProductId(Long productId);
}
