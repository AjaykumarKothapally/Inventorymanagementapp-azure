package com.Task.Shopwise.Repository;

import java.util.Optional;
import com.Task.Shopwise.Entity.Products;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface ProductRepository extends CrudRepository<Products, Long> {

	Optional<Products> findByProductId(Long productId);

}
