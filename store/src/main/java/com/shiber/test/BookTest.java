/**
 * 
 */
package com.shiber.test;

import org.junit.Before;
import org.junit.Test;

import com.shiber.bean.Book;
import com.shiber.bean.Category;
import com.shiber.dao.BookDao;
import com.shiber.dao.impl.BookDaoImpl;
import com.shiber.service.BookService;
import com.shiber.service.impl.BookServiceImpl;

/**
 * @author String
 *20208月14日
 */
public class BookTest {
	private BookService bookService;
	private BookDao bookdao;
	@Before
	public void init() {
		bookdao = new BookDaoImpl();
		bookService = new BookServiceImpl();
	}
	@Test
	public void add() {
		Book book = new Book();
		book.setPrice(1);
		Category category = new Category();
		category.setId(1);
	}
}
