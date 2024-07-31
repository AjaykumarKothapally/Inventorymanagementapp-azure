package com.Task.Manfacturer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Task.Manfacturer.Entity.ManfacturerEntity;
import com.Task.Manfacturer.Repository.ManfacturerRepository;

import java.util.Optional;

@Service
public class ManfacturerService {

	@Autowired
	private ManfacturerRepository manfacturerRepository;

	public Optional<ManfacturerEntity> getProductForUpdate(Long productId, Long requiredQuantity) throws Exception {
		try {
			return manfacturerRepository.findByProductId(productId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error retrieving product for update", e);
		}
	}
}

// private Queue<ManfacturerEntity> productUpdateQueue = new
// ConcurrentLinkedQueue<>();
//
//
// public void addToQueue(ManfacturerEntity product) {
// productUpdateQueue.add(product);
// }
//
// public Queue<ManfacturerEntity> getProductQueue() {
// return productUpdateQueue;
// }
