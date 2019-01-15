package org.motion.persistence;

import org.motion.model.productType.Coffee;
import org.motion.model.productType.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends MongoRepository<Coffee, String> {

}
