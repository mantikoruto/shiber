package com.shiber.service;

import java.util.List;

import com.shiber.bean.Category;
import com.shiber.dao.CategoryDao;
import com.shiber.dao.impl.CategroyDaoimpl;
import com.shiber.gezu.StoreResult;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao = new CategroyDaoimpl();

	@Override
	public StoreResult addCategory(Category category) {
		boolean checkCategoryName = checkCategoryName(category.getCategoryName());
		if (checkCategoryName) {
			return StoreResult.build(500, "添加失败,分类名称重复", null);
		}
		int cont = categoryDao.addCategory(category);
		if (cont == 0) {
			return StoreResult.build(500, "添加失败", null);
		}
		return StoreResult.yoshi(200, "修改成功");
	}

	@Override
	public StoreResult updateCategory(Category category) {
		Category isCategory = null;

		isCategory = categoryDao.findCategoryById(category.getId());
		if (isCategory == null) {
			return StoreResult.build(500, "修改失败,该分类不存在", null);
		}
		
		int count = categoryDao.updateCategory(category);
		if (count == 0) {
			return StoreResult.build(500, "修改失败", null);
		}
		return StoreResult.yoshi(200, "修改成功");
	}

	@Override
	public StoreResult deleteCategory(int categoryId) {
		int cont = categoryDao.deleteCategory(categoryId);
		if (cont == 0) {
			return StoreResult.build(500, "删除失败", null);
		}
		return null;
	}

	@Override
	public List<Category> findcategories() {
		List<Category> categorys = categoryDao.findCategoryAll();
		return categorys;

	}

	@Override
	public Category findCategoryById(int categoryId) {
		Category category = categoryDao.findCategoryById(categoryId);

		return category;
	}

	@Override
	public boolean checkCategoryName(String categoryName) {
		Category category = categoryDao.checkCategoryName(categoryName);
		if (category != null) {
			return true;
		}
		return false;
	}

	
}
