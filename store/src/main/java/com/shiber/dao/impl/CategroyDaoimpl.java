package com.shiber.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shiber.bean.Book;
import com.shiber.bean.Category;
import com.shiber.dao.CategoryDao;
import com.shiber.utils.DbcpUtils;

public class CategroyDaoimpl implements CategoryDao {

	@Override
	public int addCategory(Category category) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DbcpUtils.getConnection();
			statement = connection.prepareStatement("INSERT INTO category(categoryName,des) VALUE(?,?)");
			statement.setString(1, category.getCategoryName());
			statement.setString(2, category.getDes());
			int count = statement.executeUpdate();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DbcpUtils.closeAll(connection, statement, null);
		}
		return 0;
	}

	@Override
	public int updateCategory(Category category) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DbcpUtils.getConnection();
			statement = connection.prepareStatement("UPDATE category SET categoryName = ?,des = ? WHERE id = ? ");
			statement.setString(1, category.getCategoryName());
			statement.setString(2, category.getDes());
			statement.setInt(3, category.getId());
			int count = statement.executeUpdate();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DbcpUtils.closeAll(connection, statement, null);
		}
		return 0;
	}

	@Override
	public int deleteCategory(int categoryId) {
		Connection connection = null;
		Connection connection1 = null;
		PreparedStatement statement = null;
		PreparedStatement statement1 = null;
		try {
			connection = DbcpUtils.getConnection();
			statement = connection.prepareStatement("DELETE FROM category WHERE id =?");
			statement.setInt(1, categoryId);
			int count = statement.executeUpdate();
			connection1 = DbcpUtils.getConnection();
			statement1 = connection1.prepareStatement("DELETE FROM hon WHERE categoryId =?");
			count = statement1.executeUpdate();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DbcpUtils.closeAll(connection, statement, null);
			DbcpUtils.closeAll(connection1, statement1, null);
		}
		return 0;
	}

	@Override
	public Category findCategoryById(int categoryId) {
		Connection connection = null;
		Connection connection1 = null;
		PreparedStatement statement = null;
		PreparedStatement statement1 = null;
		ResultSet executeQuery = null;
		ResultSet executeQuery1 = null;
		try {
			connection = DbcpUtils.getConnection();
			statement = connection.prepareStatement("SELECT * FROM category WHERE id = ?");
			statement.setInt(1, categoryId);
			executeQuery = statement.executeQuery();
			if (executeQuery.next()) {
				Category category = new Category();
				category.setCategoryName(executeQuery.getString("categoryName"));
				category.setDes(executeQuery.getString("des"));
				category.setId(executeQuery.getInt("id"));
				connection1 = DbcpUtils.getConnection();
				statement1 = connection1.prepareStatement("SELECT * FROM hon WHERE categoryId = ?");
				statement1.setInt(1, categoryId);
				executeQuery1 = statement1.executeQuery();
				List<Book> books = new ArrayList<Book>();
				while (executeQuery1.next()) {
					Book book  = new Book();
					book.setId(executeQuery1.getInt("id"));
					book.setBookName(executeQuery1.getString("bookName"));
					book.setDes(executeQuery1.getString("des"));
					book.setPrice(executeQuery1.getInt("price"));
					book.setImg(executeQuery1.getString("pic"));
					book.setSellPoint(executeQuery1.getString("sellPoint"));
					book.setCategory(category);
					books.add(book);
				}
				category.setBooks(books);
				return category;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DbcpUtils.closeAll(connection, statement, null);
		}
		return null;
	}

	@Override
	public List<Category> findCategoryAll() {
		Connection connection = null;
		Connection connection1 = null;
		PreparedStatement statement = null;
		PreparedStatement statement1 = null;
		ResultSet executeQuery = null;
		ResultSet executeQuery1 = null;
		try {
			connection = DbcpUtils.getConnection();
			connection1 = DbcpUtils.getConnection();
			statement = connection.prepareStatement("SELECT * FROM category");
			executeQuery = statement.executeQuery();
			List<Category> categories = new ArrayList<Category>();
			while(executeQuery.next()) {
				Category category = new Category();
				category.setCategoryName(executeQuery.getString("categoryName"));
				category.setDes(executeQuery.getString("des"));
				category.setId(executeQuery.getInt("id"));
				statement1 = connection1.prepareStatement("SELECT * FROM hon WHERE categoryId = ?");
				statement1.setInt(1, category.getId());
				executeQuery1 = statement1.executeQuery();
				List<Book> books = new ArrayList<Book>();
				while (executeQuery1.next()) {
					Book book  = new Book();
					book.setId(executeQuery1.getInt("id"));
					book.setBookName(executeQuery1.getString("bookName"));
					book.setDes(executeQuery1.getString("des"));
					book.setPrice(executeQuery1.getInt("price"));
					book.setImg(executeQuery1.getString("pic"));
					book.setSellPoint(executeQuery1.getString("sellPoint"));
					book.setCategory(category);
					books.add(book);
				}
				category.setBooks(books);
				categories.add(category);
			}
			return categories;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DbcpUtils.closeAll(connection, statement, null);
			DbcpUtils.closeAll(connection1, statement1, null);
		}
		return null;
	}

	@Override
	public Category checkCategoryName(String categoryName) {
		Connection conn = DbcpUtils.getConnection();
		String sql = "SELECT * FROM category WHERE categoryName = ?";
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, categoryName);
			resultSet = pst.executeQuery();
			if(resultSet.next()){
				Category category = new Category();
				category.setId(resultSet.getInt("id"));
				category.setCategoryName(resultSet.getString("categoryName"));
				category.setDes(resultSet.getString("des"));
				return category;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbcpUtils.closeAll(conn, pst, resultSet);
		}
		return null;
	}

}
