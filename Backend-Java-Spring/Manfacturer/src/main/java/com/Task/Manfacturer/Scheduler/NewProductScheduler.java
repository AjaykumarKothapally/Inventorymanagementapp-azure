package com.Task.Manfacturer.Scheduler;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.Task.Manfacturer.Entity.ManfacturerEntity;
import com.Task.Manfacturer.Repository.ManfacturerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@ConfigurationProperties(prefix = "ecommerce")
public class NewProductScheduler {

	private static final Logger logger = LoggerFactory.getLogger(NewProductScheduler.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ManfacturerRepository manfacturerRepository;

	private Map<String, String> urls;

	public void setUrls(Map<String, String> urls) {
		this.urls = urls;
	}

	@Scheduled(fixedRate = 60000) // It will run every 1 minute
	public void updateProductQuantitiesScheduled() {
		Long targetQuantity = 1500L;

		for (Map.Entry<String, String> entry : urls.entrySet()) {
			String ecommerceKey = entry.getKey();
			String ecommerceUrl = entry.getValue();

			try {
				// Fetch all products from the e-commerce application using the URLs
				ResponseEntity<List<ManfacturerEntity>> responseEntity = restTemplate.exchange(
						ecommerceUrl + "/all", HttpMethod.GET, null,
						new ParameterizedTypeReference<List<ManfacturerEntity>>() {
						});

				logger.info("Response Received for " + ecommerceKey + " : " + responseEntity);

				List<ManfacturerEntity> ecommerceProducts = responseEntity.getBody();

				// Fetch all products from the manufacturer
				List<ManfacturerEntity> manufacturerProducts = (List<ManfacturerEntity>) manfacturerRepository
						.findAll();

				updateMissingProductsInEcommerce(ecommerceKey, targetQuantity, ecommerceProducts, manufacturerProducts);

				logger.info("Products updated successfully for " + ecommerceKey);

			} catch (RestClientException ex) {
				handleRestClientException(ecommerceKey, ex);
			}
		}
	}

	private void updateMissingProductsInEcommerce(String ecommerceKey, Long targetQuantity,
			List<ManfacturerEntity> ecommerceProducts, List<ManfacturerEntity> manufacturerProducts) {
		for (ManfacturerEntity manufacturerProduct : manufacturerProducts) {
			// Check if the product is not present in the e-commerce database
			if (!isProductPresentInEcommerce(manufacturerProduct, ecommerceProducts)) {
				// If not present, update the product quantity in the e-commerce database and
				// manufacturer database
				updateProductQuantities(manufacturerProduct, ecommerceKey, targetQuantity);
			}
		}
	}

	private boolean isProductPresentInEcommerce(ManfacturerEntity manufacturerProduct,
			List<ManfacturerEntity> ecommerceProducts) {
		return ecommerceProducts.stream()
				.anyMatch(
						ecommerceProduct -> ecommerceProduct.getProductId().equals(manufacturerProduct.getProductId()));
	}

	private void updateProductQuantities(ManfacturerEntity manufacturerProduct, String ecommerceKey,
			Long targetQuantity) {
		// Update the product quantity in the e-commerce database
		ManfacturerEntity ecommerceProduct = new ManfacturerEntity();
		ecommerceProduct.setCategoryId(manufacturerProduct.getCategoryId());
		ecommerceProduct.setProductId(manufacturerProduct.getProductId());
		ecommerceProduct.setProductQty(targetQuantity);
		ecommerceProduct.setProductSku(manufacturerProduct.getProductSku());
		ecommerceProduct.setProductName(manufacturerProduct.getProductName());
		ecommerceProduct.setProductPrice(manufacturerProduct.getProductPrice());
		ecommerceProduct.setProductShortName(manufacturerProduct.getProductShortName());
		ecommerceProduct.setProductDescription(manufacturerProduct.getProductDescription());
		ecommerceProduct.setCreatedDate(manufacturerProduct.getCreatedDate());
		ecommerceProduct.setDeliveryTimeSpan(manufacturerProduct.getDeliveryTimeSpan());
		ecommerceProduct.setProductImageUrl(manufacturerProduct.getProductImageUrl());
		ecommerceProduct.setCategoryName(manufacturerProduct.getCategoryName());

		restTemplate.postForObject(urls.get(ecommerceKey) + "/add", List.of(ecommerceProduct), List.class);

		// Update the product quantity in the Manufacturer database
		try {
			Optional<ManfacturerEntity> manufacturerEntityOptional = manfacturerRepository
					.findByProductId(manufacturerProduct.getProductId());
			if (manufacturerEntityOptional.isPresent()) {
				ManfacturerEntity existingManufacturerProduct = manufacturerEntityOptional.get();
				existingManufacturerProduct.setProductQty(existingManufacturerProduct.getProductQty() - targetQuantity);
				manfacturerRepository.save(existingManufacturerProduct);
			} else {
				logger.error("Manufacturer product with ID {} not found in database.",
						manufacturerProduct.getProductId());
			}
		} catch (Exception e) {
			logger.error("Error updating manufacturer product quantity for ID {}: {}",
					manufacturerProduct.getProductId(), e.getMessage());
		}
	}

	private void handleRestClientException(String ecommerceKey, RestClientException ex) {
		logger.error("Error connecting to the e-commerce application for {}", ecommerceKey, ex);
	}
}
