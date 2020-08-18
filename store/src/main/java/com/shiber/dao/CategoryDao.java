package com.shiber.dao;

import java.util.List;

import com.shiber.bean.Category;

public interface CategoryDao {
	int addCategory(Category category);

	int updateCategory(Category category);

	int deleteCategory(int categoryId);

	Category findCategoryById(int categoryId);

	List<Category> findCategoryAll();

	Category checkCategoryName(String categoryName);
}
