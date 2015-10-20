package com.arctic.apdu.management.dao;

import java.util.List;

import com.arctic.apdu.management.model.Categories;

public interface CategoriesDAO {
	
	public List<Categories> getAvailableCategories(List<String> catgCodeSet);

}
