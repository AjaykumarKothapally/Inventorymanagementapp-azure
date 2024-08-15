package com.Task.ShopClues.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Task.ShopClues.Entity.Products;
import com.Task.ShopClues.Repository.ShopCluesProductRepository;

@RestController
@CrossOrigin(origins = "http://www.ecomcloudconnect.com")
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ShopCluesProductRepository productRepository;

	@SuppressWarnings("deprecation")
	@GetMapping("/all")
	public ResponseEntity<?> getAllProducts() {
		List<Products> products = StreamSupport.stream(productRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
		if (products.isEmpty()) {
			int statusCode = ResponseEntity.status(HttpStatus.NOT_FOUND).build().getStatusCodeValue();
			return ResponseEntity.status(statusCode).body(statusCode);
		}
		return ResponseEntity.ok(products);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addingProducts(@RequestBody List<Products> products) {
		try {
			productRepository.saveAll(products);
			return ResponseEntity.status(HttpStatus.CREATED).body("Products added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while saving products");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Products product) {
		Optional<Products> existingProductOptional = productRepository.findByProductId(product.getProductId());
		if (existingProductOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Products existingProduct = existingProductOptional.get();
		existingProduct.setProductQty(product.getProductQty());

		Products savedProduct = productRepository.save(existingProduct);
		return ResponseEntity.ok(savedProduct);
	}

};
