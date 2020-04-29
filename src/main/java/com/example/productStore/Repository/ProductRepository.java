package com.example.productStore.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.productStore.Entity.ProductEntity;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, Long>{

	public ProductEntity findById(long id);

}
