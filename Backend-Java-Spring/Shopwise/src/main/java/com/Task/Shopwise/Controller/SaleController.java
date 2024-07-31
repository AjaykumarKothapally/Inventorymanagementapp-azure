package com.Task.Shopwise.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Task.Shopwise.Entity.SaleItem;
import com.Task.Shopwise.Entity.SaleRequest;
import com.Task.Shopwise.Service.ProductService;

@RestController
@RequestMapping("/sales")
@CrossOrigin(origins = "http://a18a849e70df749ccb319a79f8465320-976589414.ap-south-1.elb.amazonaws.com ") // frontendapploadbalacer
public class SaleController {

	@Autowired
	private ProductService productService;

	@PostMapping("/makeSale")
	public ResponseEntity<String> makeSale(@RequestBody SaleRequest saleRequest) {
		try {
			// Process the sale (save to the database, etc.)
			// Update product quantities based on the saleRequest
			for (SaleItem saleItem : saleRequest.getCartItems()) {
				productService.updateProductQuantities(saleItem.getProductId(), saleItem.getQuantity());
			}
			return ResponseEntity.ok().body("{\"message\": \"Sale successful\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing sale");
		}
	}
}
