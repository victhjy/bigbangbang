package com.hjy.service;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.hjy.model.BooksModel;
import com.hjy.model.LoginIn;
@WebService
public interface AllApiInterface {
		
	//获取所有图书信息
	String getAllBooksInfo();
	
	//登录验证
	String loginIn(String password,String schoolId);
	
	//通过书名搜索图书
	String searchBooksWithTitle(String title);
	
	//通过作者搜索图书
	String searchBooksWithAuthor(String author);
	
	//通过ISBN搜索图书
	String searchBooksWithISBN(String ISBN);
	
	//获取用户个人信息
	String getUserModel(String schoolId);
	
	//获取我的借阅列表
	String[] getMyBorrowBooksList(String userId);

	//获取我的收藏列表
	String getMyFavBooksList(String userId);
	
	//续借图书
	String renew(String userId,String bookId,String time);
	
	//收藏图书
	String addMyFav(String userId,String bookId);
	
	//取消收藏
	String deleteMyFav(String userId,String bookId);
	
	//获取热门图书
	String getRankBooks();
	
	//获取图书馆公告
	String getLibraryBoard();

	
}
