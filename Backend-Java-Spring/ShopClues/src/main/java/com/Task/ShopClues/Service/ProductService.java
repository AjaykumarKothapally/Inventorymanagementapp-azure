package com.Task.ShopClues.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Task.ShopClues.Entity.Products;
import com.Task.ShopClues.Repository.ShopCluesProductRepository;

@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	@Autowired
	private ShopCluesProductRepository productRepository;

	public void updateProductQuantities(Long productId, Long quantity) {
		Optional<Products> productOptional = productRepository.findByProductId(productId);
		if (productOptional.isPresent()) {
			Products product = productOptional.get();
			logger.info("Fetched product: {}", product); // Log the fetched product
			Long updatedQuantity = product.getProductQty() - quantity;
			if (updatedQuantity >= 0) {
				product.setProductQty(updatedQuantity);
				productRepository.save(product);
			} else {
				throw new RuntimeException("Insufficient quantity for product: " + product.getProductName());
			}
		} else {
			throw new RuntimeException("Product not found with ID: " + productId);
		}
	}

}
