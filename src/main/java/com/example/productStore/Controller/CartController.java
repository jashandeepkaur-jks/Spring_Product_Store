package com.example.productStore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.productStore.Entity.CartEntity;
import com.example.productStore.Entity.ProductEntity;
import com.example.productStore.Repository.CartRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
	
	@Autowired
	CartRepository cartRepository;
	
	@PostMapping("/saveCart")
	public void saveCart(@RequestBody List<CartEntity> cartEntities) {
		cartRepository.saveAll(cartEntities);
	
	}
	
	@GetMapping("/getCartItems")
	public List<CartEntity> showCart(){
		List<CartEntity> productEntities=cartRepository.findAll();
		return productEntities;
		
	}
	
	@DeleteMapping("/emptyCart")
	public void emptyCart(){
		cartRepository.deleteAll();
		
		
	}
	
	@DeleteMapping(path = { "deleteCartItem/{id}" })
	public CartEntity deleteProduct(@PathVariable("id") long id) {
		CartEntity cEntity=cartRepository.findById(id);
		System.out.println("hi");
		cartRepository.deleteById(id);
		return cEntity;
	}
	


}
