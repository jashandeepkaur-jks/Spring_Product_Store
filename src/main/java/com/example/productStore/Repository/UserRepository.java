package com.example.productStore.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.productStore.Entity.UserEntity;

public interface UserRepository  extends MongoRepository<UserEntity, Long>{
  
	public UserEntity findById(long id);
}
