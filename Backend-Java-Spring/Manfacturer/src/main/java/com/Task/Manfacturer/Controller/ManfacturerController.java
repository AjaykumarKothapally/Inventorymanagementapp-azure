package com.Task.Manfacturer.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Task.Manfacturer.Entity.ManfacturerEntity;
import com.Task.Manfacturer.Repository.ManfacturerRepository;

@RestController
@RequestMapping("/products")
public class ManfacturerController {

	@Autowired
	private ManfacturerRepository manfacturerRepository;
	

	@SuppressWarnings("deprecation")
	@GetMapping("/all")
	public ResponseEntity<?> getAllProducts() {
		List<ManfacturerEntity> products = (List<ManfacturerEntity>) manfacturerRepository.findAll();
		if (products.isEmpty()) {
			int statusCode = ResponseEntity.status(HttpStatus.NOT_FOUND).build().getStatusCodeValue();
			return ResponseEntity.status(statusCode).body(statusCode);
		}
		return ResponseEntity.ok(products);
	}		
	
	@PostMapping("/addingNew")
	public ResponseEntity<?> addingorUpdating(@RequestBody List<ManfacturerEntity> products) {
	  
		List<ManfacturerEntity> savedProducts = new ArrayList<>();

	    for (ManfacturerEntity product : products) {
	        Optional<ManfacturerEntity> existingProductOptional = manfacturerRepository.findByProductId(product.getProductId());

	        if (existingProductOptional.isPresent()) {
	            ManfacturerEntity existingProduct = existingProductOptional.get();
	            existingProduct.setProductQty(existingProduct.getProductQty() + product.getProductQty());
	            ManfacturerEntity savedProduct = manfacturerRepository.save(existingProduct);
	            savedProducts.add(savedProduct);
	        } else {
	            ManfacturerEntity savedProduct = manfacturerRepository.save(product);
	            savedProducts.add(savedProduct);
	        }
	    }
	    
	    return ResponseEntity.ok(savedProducts);
	} 
	
}
	