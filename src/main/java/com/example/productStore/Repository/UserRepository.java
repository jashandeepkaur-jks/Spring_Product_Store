package com.example.productStore.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.productStore.Entity.UserEntity;

public interface UserRepository  extends MongoRepository<UserEntity, Long>{
  
	public List<UserEntity> findByName(String name);
	public UserEntity findById(long id);
}
