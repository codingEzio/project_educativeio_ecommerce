package com.educative.ecommerce.service;

import com.educative.ecommerce.dto.product.ProductDto;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.model.Product;
import com.educative.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public void addProduct(ProductDto productDto, Category category) {
		Product product = getProductFromDto(productDto, category);

		productRepository.save(product);
	}


	public static Product getProductFromDto(ProductDto productDto, Category category) {
		Product product = new Product();

		product.setDescription(productDto.getDescription());
		product.setImageUrl(productDto.getImageUrl());
		product.setPrice(productDto.getPrice());
		product.setName(productDto.getName());
		
		product.setCategory(category);

		return product;
	}
}