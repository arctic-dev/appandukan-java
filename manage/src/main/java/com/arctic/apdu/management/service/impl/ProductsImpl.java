package com.arctic.apdu.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arctic.apdu.management.dao.CategoriesDAO;
import com.arctic.apdu.management.dao.ProductsDAO;
import com.arctic.apdu.management.model.Categories;
import com.arctic.apdu.management.model.Products;
import com.arctic.apdu.management.service.IProducts;
import com.arctic.apdu.management.utils.CoreConstants;
import com.arctic.apdu.management.utils.CoreUtils;

@Service
public class ProductsImpl implements IProducts{
	
	@Autowired
	private ProductsDAO productsDAO;
	
	@Autowired
	private CategoriesDAO categoriesDAO;

	@Override
	@Transactional
	public Map<String, Object> getAvailableProducts(String userId) {
		Map<String, Object> resultMap = null;
		resultMap = new HashMap<String, Object>();
		List<Products> prodList = this.productsDAO.getAvailableProducts(userId);
		if (null != prodList && !prodList.isEmpty()) {
			resultMap.put(CoreConstants.STATUS, CoreConstants.SUCCESS);
			resultMap.put(CoreConstants.MESSAGE, "Products fetched");
			List<String> catgCodes = new ArrayList<String>();
			resultMap.put(CoreConstants.PRODUCTS, CoreUtils.processProdResult(prodList, catgCodes));
			List<Categories> catgList =  this.categoriesDAO.getAvailableCategories(catgCodes);
			resultMap.put(CoreConstants.CATEGORIES, CoreUtils.processCatgResult(catgList));
		} else {
			resultMap.put(CoreConstants.STATUS, CoreConstants.FAILURE);
			resultMap.put(CoreConstants.MESSAGE, "Products not available");
		}
		return resultMap;
	}

}
