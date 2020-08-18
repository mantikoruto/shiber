package com.shiber.service;

import java.util.ArrayList;
import java.util.List;

import com.shiber.bean.Category;
import com.shiber.gezu.StoreResult;

public interface CategoryService {
	StoreResult addCategory(Category category);

	StoreResult updateCategory(Category category);

	StoreResult deleteCategory(int category);

	List<Category> findcategories();
	
	boolean checkCategoryName(String categoryName);

	Category findCategoryById(int categoryId);
}
