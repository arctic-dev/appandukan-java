package com.arctic.apdu.management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arctic.apdu.management.service.IProducts;
import com.arctic.apdu.management.utils.CoreURIConstants;

@RestController
public class ProductsController {
	
	@Autowired
	private IProducts products;
	
	@RequestMapping(value = CoreURIConstants.GET_AVAIL_PRODS, method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAvailableProducts(@PathVariable("userId") String userId){
		return new ResponseEntity<Map<String, Object>>(products.getAvailableProducts(userId), HttpStatus.OK);
	}

}
