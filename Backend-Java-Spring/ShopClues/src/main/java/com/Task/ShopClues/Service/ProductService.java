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
			Long updatedQuantity = product.getProductQty() - quantity;

			if (updatedQuantity >= 0) {
				logger.info("Updating product quantity for productId {}: current quantity {}, new quantity {}",
						productId, product.getProductQty(), updatedQuantity);
				product.setProductQty(updatedQuantity);
				productRepository.save(product);
			} else {
				logger.error("Insufficient quantity for product: {}", product.getProductName());
				throw new RuntimeException("Insufficient quantity for product: " + product.getProductName());
			}
		} else {
			logger.error("Product not found with ID: {}", productId);
			throw new RuntimeException("Product not found with ID: " + productId);
		}
	}

}
