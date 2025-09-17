package com.mtd.EcomApp.service;
import org.springframework.stereotype.Service;
import com.mtd.EcomApp.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.mtd.EcomApp.entity.Product;


@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	 
	//save
	public Product saveProduct(Product product) {
		product.setId(null);
		return productRepository.save(product);
	}
	//get by Id
	public Product getProductById(String id) {
	return productRepository.findById(id).get();
	}
	//get all products
	public List<Product> getProducts(){
		return productRepository.findAll();
	}
	//update
	public Product updateProduct(Product product, String id) {
		Product existingProduct=productRepository.findById(id).get();
		
		existingProduct.setName(product.getName());
		existingProduct.setCategory(product.getCategory());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setTags(product.getTags());
		existingProduct.setStock(product.getStock());
		
		return productRepository.save(existingProduct);
	}
	
	//delete
	public boolean deleteProduct(String id) {
		Optional<Product> product=productRepository.findById(id);
		if(product.isEmpty()) {
			return false;
		}
		productRepository.deleteById(id);
		return true;
	}

}
