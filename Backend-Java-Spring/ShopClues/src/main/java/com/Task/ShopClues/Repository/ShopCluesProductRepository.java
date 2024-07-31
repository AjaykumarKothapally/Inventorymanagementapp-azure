package com.Task.ShopClues.Repository;

import java.util.Optional;
import com.Task.ShopClues.Entity.Products;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface ShopCluesProductRepository extends CrudRepository<Products, String> {

	Optional<Products> findByProductId(Long productId);
}
