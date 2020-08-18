/**
 * 
 */
package com.shiber.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shiber.bean.Book;
import com.shiber.bean.Category;
import com.shiber.dao.BookDao;
import com.shiber.service.CategoryService;
import com.shiber.service.CategoryServiceImpl;
import com.shiber.utils.DbcpUtils;

/**
 * @author String 2020年8月14日
 */
public class BookDaoImpl implements BookDao {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shiber.dao.BookDao#addBook()
	 */
	@Override
	public int addBook(Book book) {
		PreparedStatement prepareStatement = null;
		Connection connection = DbcpUtils.getConnection();
		try {
			prepareStatement = connection
			.prepareStatement("INSERT INTO hon(bookName,sellPoint,pic,des,price,categoryId) VALUE(?,?,?,?,?,?)");
			prepareStatement.setString(1, book.getBookName());
			prepareStatement.setString(2, book.getSellPoint());
			prepareStatement.setString(4, book.getDes());
			prepareStatement.setString(3, book.getPic());
			prepareStatement.setInt(5, book.getPrice());
			Category category = book.getCategory();
			prepareStatement.setInt(6, category.getId());
			int update = prepareStatement.executeUpdate();
			return update;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shiber.dao.BookDao#deleteBook(int)
	 */
	@Override
	public int deleteBook(int bookId) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DbcpUtils.getConnection();
			statement = connection.prepareStatement("DELETE FROM hon WHERE id =?");
			statement.setInt(1, bookId);
			int count = statement.executeUpdate();

			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbcpUtils.closeAll(connection, statement, null);

		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shiber.dao.BookDao#updateBook(com.shiber.bean.Book)
	 */
	@Override
	public int updateBook(Book book) {
		PreparedStatement prepareStatement = null;
		Connection connection = DbcpUtils.getConnection();
		try {
			prepareStatement = connection
			.prepareStatement("UPDATE hon SET bookName = ?,sellPoint = ?, price =?,des = ?,pic = ?,categoryId = ? WHERE id = ?");
			prepareStatement.setString(1, book.getBookName());
			prepareStatement.setString(2, book.getSellPoint());
			prepareStatement.setString(4, book.getDes());
			prepareStatement.setString(5, book.getPic());
			prepareStatement.setInt(3, book.getPrice());
			Category category = book.getCategory();
			prepareStatement.setInt(6, category.getId());
			prepareStatement.setInt(7, book.getId());
			int update = prepareStatement.executeUpdate();
			return update;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shiber.dao.BookDao#findBookById(int)
	 */
	@Override
	public Book findBookById(int bookId) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet executeUpdate = null;
		try {
			connection = DbcpUtils.getConnection();
			statement = connection.prepareStatement("SELECT * FROM hon WHERE id =?");
			statement.setInt(1, bookId);
			executeUpdate = statement.executeQuery();
			if (executeUpdate.next()) {
				Book book = new Book();
				book.setId(executeUpdate.getInt("id"));
				book.setBookName(executeUpdate.getString("bookName"));
				book.setDes(executeUpdate.getString("des"));
				book.setImg(executeUpdate.getString("pic"));
				book.setSellPoint(executeUpdate.getString("sellPoint"));
				book.setPrice(executeUpdate.getInt("price"));
				int categoryId = executeUpdate.getInt("categoryId");
				CategoryService service = new CategoryServiceImpl();
				Category category= service.findCategoryById(categoryId);
				book.setCategory(category);
				return book;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbcpUtils.closeAll(connection, statement,executeUpdate);

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shiber.dao.BookDao#findBooks()
	 */
	@Override
	public List<Book> findBooks() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet executeUpdate = null;
		
		try {
			connection = DbcpUtils.getConnection();
			statement = connection.prepareStatement("SELECT * FROM hon");
			executeUpdate = statement.executeQuery();
			List<Book> books = new ArrayList<Book>();
			CategoryService service = new CategoryServiceImpl();
			while(executeUpdate.next()) {
				Book book = new Book();
				book.setId(executeUpdate.getInt("id"));
				book.setBookName(executeUpdate.getString("bookName"));
				book.setDes(executeUpdate.getString("des"));
				book.setImg(executeUpdate.getString("pic"));
				book.setSellPoint(executeUpdate.getString("sellPoint"));
				book.setPrice(executeUpdate.getInt("price"));
				int categoryId = executeUpdate.getInt("categoryId");
				Category category= service.findCategoryById(categoryId);
				book.setCategory(category);
				books.add(book);
			}
			return books;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbcpUtils.closeAll(connection, statement,executeUpdate);

		}
		return null;
	}

}
