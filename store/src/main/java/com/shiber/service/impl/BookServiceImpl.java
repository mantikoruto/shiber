/**
 * 
 */
package com.shiber.service.impl;

import java.util.List;

import com.shiber.bean.Book;
import com.shiber.dao.BookDao;
import com.shiber.dao.impl.BookDaoImpl;
import com.shiber.gezu.StoreResult;
import com.shiber.service.BookService;

/**
 * @author String
 *2020年8月14日
 */
public class BookServiceImpl implements BookService{

	private BookDao bookDao = new BookDaoImpl();
	@Override
	public StoreResult addBook(Book book) {
		int count = bookDao.addBook(book);
		if (count==0) {
			return StoreResult.build(500, "添加失败", null);
		}
		return StoreResult.yoshi(200, "成功");
	}

	@Override
	public StoreResult deleteBook(int bookId) {
		int count = bookDao.deleteBook(bookId);
		return null;
	}

	
	@Override
	public StoreResult updateBook(Book book) {
		int count = bookDao.updateBook(book);
		return null;
	}

	
	@Override
	public Book findBookById(int bookId) {
		Book book = bookDao.findBookById(bookId);
		return null;
	}

	
	@Override
	public List<Book> findBooks() {
		List<Book> books = bookDao.findBooks();
		return null;
	}

}
