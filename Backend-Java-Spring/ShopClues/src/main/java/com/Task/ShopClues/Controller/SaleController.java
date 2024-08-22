package com.Task.ShopClues.Controller;

import com.Task.ShopClues.Entity.SaleItem;
import com.Task.ShopClues.Entity.SaleRequest;
import com.Task.ShopClues.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private static final Logger logger = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private ProductService productService;

    @PostMapping("/makeSale")
    public ResponseEntity<String> makeSale(@RequestBody SaleRequest saleRequest) {
        try {

            logger.info("Received sale request: {}", saleRequest);

            // Validate the saleRequest
            if (saleRequest.getCartItems() == null || saleRequest.getCartItems().isEmpty()) {
                return ResponseEntity.badRequest().body("{\"message\": \"No items in the cart\"}");
            }

            for (SaleItem saleItem : saleRequest.getCartItems()) {
                productService.updateProductQuantities(saleItem.getProductId(), saleItem.getQuantity());
            }
            return ResponseEntity.ok().body("{\"message\": \"Sale successful\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing sale");
        }
    }
}
