package com.shiber.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.shiber.bean.Book;
import com.shiber.bean.Category;
import com.shiber.gezu.StoreResult;
import com.shiber.service.BookService;
import com.shiber.service.CategoryService;
import com.shiber.service.CategoryServiceImpl;
import com.shiber.service.impl.BookServiceImpl;
import com.shiber.utils.IdGenerator;

/**
 * Servlet implementation class UploaderController
 */
public class UploaderController extends HttpServlet {
	private CategoryService categoryService;
	private BookService bookService;
		@Override
		public void init() throws ServletException {
			super.init();
			categoryService= new CategoryServiceImpl();
			bookService= new BookServiceImpl();
		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean multipartContent = ServletFileUpload.isMultipartContent(request);
		if (!multipartContent) {
			System.out.println("不能上传");
			return;
		}
		DiskFileItemFactory diskFileItemFactory= new DiskFileItemFactory();
		ServletFileUpload servletFileUpload= new ServletFileUpload(diskFileItemFactory);
		try {
			List<FileItem> parseRequest = servletFileUpload.parseRequest(request);
			Book book= new Book();
			for (FileItem fileItem : parseRequest) {
				if (fileItem.isFormField()) {
					String fieldName = fileItem.getFieldName();
					String value = fileItem.getString("utf-8");
					System.out.println(fieldName+":"+value);
					if ("bookName".equals(fieldName)) {
						book.setBookName(value);
					}
					else if("sellPoint".equals(fieldName)){
						book.setSellPoint(value);
					}else if("price".equals(fieldName)){
						book.setPrice(Integer.valueOf(value));
					}else if("categoryId".equals(fieldName)){
						Category category = categoryService.findCategoryById(Integer.valueOf(value));
						book.setCategory(category);
					}else if("des".equals(fieldName)){
						book.setDes(value);
					}
				}else {
					String name = fileItem.getName();
					String filename = FilenameUtils.getName(name);
					filename = IdGenerator.getGenerator(filename);
					Date date= new Date();
					SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
					String format = simpleDateFormat.format(date);
					File file= new File("E://img",format);
					if (!file.exists()) {
						file.mkdir();
					}
					fileItem.write(new File(file,name));
					book.setImg("http://192.168.0.160/"+format+"/"+filename);
				}
				
			}
			StoreResult addBook = bookService.addBook(book);
			response.sendRedirect("/store/manager");
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
