package com.example.productStore.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.productStore.Entity.ProductEntity;
import com.example.productStore.Repository.ProductRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductCotroller {
	
	private byte[] bytes;
	
	@Autowired
	ProductRepository productRepository;
	
	@PostMapping(value = "/uploadImage")
	public void uploadImage(@RequestParam("imageFile") MultipartFile img) throws IOException{
		this.bytes=img.getBytes();
	}

	/*
	 * @PostMapping(value = "/uploadImageWithData") public void
	 * uploadImage(@RequestPart("file") MultipartFile file,@RequestPart("json")
	 * ProductEntity p ) throws IOException{
	 * 
	 * }
	 */
	@PostMapping(value = "/saveProduct")
	public void saveProduct(@RequestBody ProductEntity p){
		p.setImage(this.bytes);
		productRepository.save(p);
		this.bytes=null;
	}
	
	@GetMapping("/showProducts")
	public List<ProductEntity> showProducts(){
		List<ProductEntity> productEntities=productRepository.findAll();
		return productEntities;
		
	}
	
	@DeleteMapping(path = { "deleteProduct/{id}" })
	public ProductEntity deleteProduct(@PathVariable("id") long id) {
		ProductEntity productEntity = productRepository.findById(id);
		productRepository.deleteById(id);
		return productEntity;
	}
	
	@PutMapping("/updateProduct")
	public void updateProduct(@RequestBody ProductEntity prEntity) {
		prEntity.setImage(this.bytes);
		productRepository.save(prEntity);
		this.bytes=null;
	}
	
	@GetMapping("/getProductByid/{id}")
	public ProductEntity fectchProduct(@PathVariable("id") long id) {
		ProductEntity pEntity=productRepository.findById(id);
		return pEntity;
	}
	
	@PutMapping("/buyNow")
	public void checkOut(@RequestBody ProductEntity... prEntity) {
		System.out.println("buy");
		for(ProductEntity p:prEntity) {
			productRepository.deleteById(p.getId());
		}
	}
	

}
