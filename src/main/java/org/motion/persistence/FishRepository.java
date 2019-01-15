package org.motion.persistence;

import org.motion.model.productType.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishRepository extends MongoRepository<Product, String> {

}
