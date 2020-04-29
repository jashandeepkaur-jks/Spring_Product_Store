package com.example.productStore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.productStore.Entity.UserEntity;
import com.example.productStore.Repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {
	
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping(value = "/saveUser")
	public void saveUser(@RequestBody UserEntity p){
		userRepository.save(p);
	}
	
	@GetMapping("/showUsers")
	public List<UserEntity> showUser(){
		List<UserEntity> userEntities=userRepository.findAll();
		return userEntities;
		
	}
	
	@DeleteMapping(path = { "deleteUser/{id}" })
	public UserEntity deleteUser(@PathVariable("id") long id) {
		UserEntity userEntity = userRepository.findById(id);
		userRepository.deleteById(id);
		return userEntity;
	}
	
	@PutMapping("/updateUser")
	public void updateUser(@RequestBody UserEntity urEntity) {
		userRepository.save(urEntity);
	}
	

}
