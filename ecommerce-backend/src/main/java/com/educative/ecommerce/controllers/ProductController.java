package com.educative.ecommerce.controllers;


import com.educative.ecommerce.common.ApiResponse;
import com.educative.ecommerce.dto.product.ProductDto;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.service.CategoryService;
import com.educative.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService prodService;

	@Autowired
	CategoryService cateService;

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
		Optional<Category> opCategory = cateService.readCategory(productDto.getCategoryId());

		if (!opCategory.isPresent()) {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(false, "category is invalid"),
					HttpStatus.CONFLICT
			);
		}

		Category category = opCategory.get();
		prodService.addProduct(productDto, category);

		return new ResponseEntity<>(
				new ApiResponse(true, "Product has been added"),
				HttpStatus.CREATED
		);
	}

}