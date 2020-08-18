package com.shiber.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.shiber.bean.Category;
import com.shiber.dao.CategoryDao;
import com.shiber.dao.impl.CategroyDaoimpl;

public class CateoryTest {
	private CategoryDao cDao; 
	@Before
	public void init(){
		cDao = new CategroyDaoimpl();
	}
	@Test
	public void add(){
		Category category = new Category();
		category.setCategoryName("玄幻");
		category.setDes("东方玄幻，西方玄幻");
		cDao.addCategory(category);
	}
	@Test
	public void findDao(){
		Category byId = cDao.findCategoryById(2);
		System.out.println(byId);
	}
	@Test
	public void updateDao(){
		Category category = new Category();
		category.setCategoryName("女生");
		category.setDes("穿越，言情");
		category.setId(2);
		cDao.updateCategory(category);
	}
	@Test
	public void deleteDao(){
		int deleteCategoryById = cDao.deleteCategory(1);
	}
	@Test
	public void findallDao(){
		List<Category> findCategorys = cDao.findCategoryAll();
		for (Category category : findCategorys) {
			System.out.println(category);
		}
	}
}
