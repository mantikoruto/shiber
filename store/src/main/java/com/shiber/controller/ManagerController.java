package com.shiber.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shiber.bean.Book;
import com.shiber.bean.Category;
import com.shiber.gezu.BookVo;
import com.shiber.gezu.StoreResult;
import com.shiber.service.BookService;
import com.shiber.service.CategoryService;
import com.shiber.service.CategoryServiceImpl;
import com.shiber.service.impl.BookServiceImpl;
import com.shiber.utils.JsonUtils;

/**
 * Servlet implementation class ManagerController
 */
public class ManagerController extends HttpServlet {
	private CategoryService categoryService;
	private BookService bookservice;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		categoryService = new CategoryServiceImpl();
		bookservice = new BookServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parameter = request.getParameter("op");
		if ("checkCategoryName".equals(parameter)) {
			checkCategoryName(request, response);
		} else if ("addCategory".equals(parameter)) {
			addCategory(request, response);
		} else if ("deleteCategory".equals(parameter)) {
			deleteCategory(request, response);
		} else if ("showCategoryAll".equals(parameter)) {
			showCategoryAll(request, response);
		} else if ("showUpdateCategory".equals(parameter)) {
			showUpdateCategory(request,response);
		}else if("updateCategory".equals(parameter)){
			updateCategory(request,response);
		}else if("findCategories".equals(parameter)){
			findCategories(request,response);
		}else if("findBooks".equals(parameter)){
			findBooks(request,response);
		}
	}

	private void findBooks(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
				List<Book> findBooks = bookservice.findBooks();
				List<BookVo> bookVos= new ArrayList<BookVo>();
				for (Book book : findBooks) {
					BookVo bookVo= new BookVo();
					bookVo.setBook(book);
					bookVo.setCategoryId(book.getCategory().getId());
					bookVo.setCategoryDes(book.getCategory().getDes());
					bookVo.setCategoryName(book.getCategory().getCategoryName());
					book.setCategory(null);
					bookVo.setBook(book);
					bookVos.add(bookVo);
				}
				String objectToJson = JsonUtils.objectToJson(bookVos);
				response.getWriter().print(objectToJson);
	}

	private void findCategories(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		List<Category> findcategories = categoryService.findcategories();
		String objectToJson = JsonUtils.objectToJson(findcategories);
		response.getWriter().print(objectToJson);
	}

	private void updateCategory(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		Integer id = Integer.valueOf(request.getParameter("id"));
		String des = request.getParameter("des");
		String categoryName = request.getParameter("categoryName");
		Category category = new Category();
		category.setId(id);
		category.setDes(des);
		category.setCategoryName(categoryName);
		categoryService.updateCategory(category);
		response.sendRedirect("/store/manager/ManagerController?op=showCategoryAll");
		
	}

	private void showUpdateCategory(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		Integer id = Integer.valueOf(request.getParameter("id"));
		Category findCategoryById = categoryService.findCategoryById(id);
		String objectToJson = JsonUtils.objectToJson(findCategoryById);
		response.getWriter().print(objectToJson);
	}

	private void showCategoryAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> findcategories = categoryService.findcategories();
		request.setAttribute("findcategories", findcategories);
		request.getRequestDispatcher("/manager/showCategory.jsp").forward(request, response);
	}

	private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		StoreResult deleteCategory = categoryService.deleteCategory(id);
		String objectToJson = JsonUtils.objectToJson(deleteCategory);
		response.getWriter().print(objectToJson);
	}

	private void checkCategoryName(HttpServletRequest requst, HttpServletResponse response)
			throws ServletException, IOException {
		String parameter = requst.getParameter("categoryName");
		parameter = new String(parameter.getBytes("ISO-8859-1"), "UTF-8");
		boolean checkCategoryName = categoryService.checkCategoryName(parameter);
		if (checkCategoryName) {
			String json = JsonUtils.objectToJson(StoreResult.yoshi(500, "failse"));
			response.getWriter().print(json);
		} else {
			String json = JsonUtils.objectToJson(StoreResult.yoshi(200, "success"));
			response.getWriter().print(json);
		}
	}

	private void addCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cn = request.getParameter("categoryName");
		String des = request.getParameter("des");
		Category category = new Category();
		category.setCategoryName(cn);
		category.setDes(des);
		StoreResult addCategory = categoryService.addCategory(category);
		String objectToJson = JsonUtils.objectToJson(addCategory);
		response.getWriter().print(objectToJson);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
