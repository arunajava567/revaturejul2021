package com.revature.springrest.springrestdemo1.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.springrest.springrestdemo1.product.model.Product;
import com.revature.springrest.springrestdemo1.product.service.IProductService;

@RestController
@RequestMapping("/api/v1")  //"productcapp"
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	
	@PutMapping("/products") //update
	public ResponseEntity<List<Product>> updateProduct(	@RequestBody Product product){
		List<Product> products= productService.updateProduct(product);
		if(products.isEmpty())
		{
			return new ResponseEntity("Sorry! Products not available!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/products") //add 
	public ResponseEntity<List<Product>> insertProduct(	@RequestBody Product product){
		List<Product> products= productService.saveProduct(product);
		if(products.isEmpty())
		{
			return new ResponseEntity("Sorry! Products not available!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<List<Product>> deleteProduct(
			@PathVariable("productId")Integer productId){
		List<Product> products= productService.deleteProduct(productId);
		if(products.isEmpty() || products==null) {
			return new ResponseEntity("Sorry! ProductsId not available!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> findProduct(
			@PathVariable("productId")Integer productId){
		Product product= productService.findProduct(productId);
		if(product==null) {
			return new ResponseEntity("Sorry! Products not found!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	//@RequestMapping(value="/products",method=RequestMethod.GET)
 	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products= productService.getAllProducts();
		if(products.isEmpty()) {
			return new ResponseEntity("Sorry! Products not available!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
}
