package com.Task.Manfacturer.Repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.Task.Manfacturer.Entity.ManfacturerEntity;

import java.util.Optional;

@Repository
@EnableScan
public interface ManfacturerRepository extends CrudRepository<ManfacturerEntity, Long> {

	Optional<ManfacturerEntity> findByProductId(Long productId);

}
