package com.shiber.service;



import java.util.List;

import com.shiber.bean.Book;
import com.shiber.gezu.StoreResult;

public interface BookService {
	StoreResult addBook(Book book);

	StoreResult deleteBook(int bookId);

	StoreResult updateBook(Book book);

	Book findBookById(int bookId);
	
	List<Book> findBooks();
}
