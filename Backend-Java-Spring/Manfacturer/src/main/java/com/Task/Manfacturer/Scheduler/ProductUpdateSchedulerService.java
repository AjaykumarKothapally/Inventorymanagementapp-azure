package com.Task.Manfacturer.Scheduler;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Task.Manfacturer.Entity.ManfacturerEntity;
import com.Task.Manfacturer.Repository.ManfacturerRepository;
import com.Task.Manfacturer.Service.ManfacturerService;

@Service
@ConfigurationProperties(prefix = "ecommerce")
public class ProductUpdateSchedulerService {

	private static final Logger logger = LoggerFactory.getLogger(ProductUpdateSchedulerService.class);

	@Autowired
	private ManfacturerService manfacturerService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ManfacturerRepository manfacturerRepository;

	private Map<String, String> urls;

	public void setUrls(Map<String, String> urls) {
		this.urls = urls;
	}

	@Scheduled(fixedRate = 120000) // It will run for every 2 minutes.
	public void updateProductQuantitiesScheduled() throws Exception {

		Long targetQuantity = 1500L;

		for (Map.Entry<String, String> e : urls.entrySet()) {
			String ecommerceKey = String.valueOf(e.getKey());
			String ecommerceUrl = String.valueOf(e.getValue());

			// It will Fetch all products from the e-commerce application using the URLS
			ResponseEntity<List<ManfacturerEntity>> responseEntity = restTemplate.exchange(ecommerceUrl + "/all",
					HttpMethod.GET, null, new ParameterizedTypeReference<List<ManfacturerEntity>>() {
					});

			logger.info("Response Received for " + ecommerceKey + " : " + responseEntity);

			List<ManfacturerEntity> ecommerceProducts = responseEntity.getBody();

			for (ManfacturerEntity product : ecommerceProducts) {
				if (product.getProductQty() < targetQuantity) {
					Long requiredQuantity = targetQuantity - product.getProductQty();
					updateProductQuantities(product, ecommerceKey, requiredQuantity);
				}
			}

			logger.info("Products updated successfully for " + ecommerceKey);

		}
	}

	public void updateProductQuantities(ManfacturerEntity ecommerceProduct, String ecommerceKey, Long requiredQuantity)
			throws Exception {
		// It will get the required quantity from the Manufacturer database
		Long targetQuantity = 1500L;
		try {
			Optional<ManfacturerEntity> manufacturerProductOptional = manfacturerService
					.getProductForUpdate(ecommerceProduct.getProductId(), requiredQuantity);

			if (manufacturerProductOptional.isPresent()) {
				ManfacturerEntity manufacturerProduct = manufacturerProductOptional.get();

				// It will update the product quantity in the e-commerce database
				ecommerceProduct.setProductQty(targetQuantity);
				restTemplate.put(urls.get(ecommerceKey) + "/" + ecommerceProduct.getProductId(), ecommerceProduct);

				// It will Update the product quantity in the Manufacturer database
				manufacturerProduct.setProductQty(manufacturerProduct.getProductQty() - requiredQuantity);
				manfacturerRepository.save(manufacturerProduct);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error retrieving product for update", e);
		}
	}
}