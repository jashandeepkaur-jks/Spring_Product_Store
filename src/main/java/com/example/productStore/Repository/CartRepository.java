package com.example.productStore.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.productStore.Entity.CartEntity;

public interface CartRepository extends MongoRepository<CartEntity, Long>{
	public CartEntity findById(long id);
}
