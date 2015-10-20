package com.arctic.apdu.management.dao;

import java.util.List;

import com.arctic.apdu.management.model.Products;

public interface ProductsDAO {
	
	public List<Products> getAvailableProducts(String userId);
	public void updateProductDetails(Products prod);
	

}
