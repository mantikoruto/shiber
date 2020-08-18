/**
 * 
 */
package com.shiber.dao;

import java.util.List;

import com.shiber.bean.Book;

/**
 * @author String
 *2020年8月14日
 */
public interface BookDao {

	/**データベースから本を増加
	 * @return
	 */
	int addBook(Book book);

	/**データベースから本を削除
	 * @param bookId
	 * @return sqlの施行回数
	 */
	int deleteBook(int bookId);

	/**
	 * @param bookのオブジェクト
	 * @return　bookオブジェクトの変化
	 */
	int updateBook(Book book);

	/**
	 * @param bookId
	 * @return　bookオブジェクトの検索
	 */
	Book findBookById(int bookId);

	/**
	 * @return　全ての本をアピール
	 */		
	List<Book> findBooks();

}
