package com.Task.Shopwise.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.Task.Shopwise.Entity.Products;
import com.Task.Shopwise.Repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	@Autowired
	private ProductRepository productRepository;

	public void updateProductQuantities(Long productId, Long quantity) {
		Optional<Products> productOptional = productRepository.findByProductId(productId);
		if (productOptional.isPresent()) {
			Products product = productOptional.get();
			Long updatedQuantity = product.getProductQty() - quantity;

			if (updatedQuantity >= 0) {
				product.setProductQty(updatedQuantity);
				dynamoDBMapper.save(product);
			} else {
				throw new RuntimeException("Insufficient quantity for product: " + product.getProductName());
			}
		} else {
			throw new RuntimeException("Product not found with ID: " + productId);
		}
	}

}
