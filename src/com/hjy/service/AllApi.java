package com.hjy.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.hjy.jdbc.*;
import com.hjy.model.BooksModel;
import com.hjy.model.LoginIn;
import com.hjy.service.Controller;

public class AllApi implements AllApiInterface {
    
	Controller tool=new Controller();   
    
	//获取所有书籍信息
	@Override
	public String getAllBooksInfo() {
		return tool.getAllBooksInfo();
	}
    
	//登录验证
	@Override
	public String loginIn(String password,String schoolId) {
		return tool.loginIn(schoolId,password);
	}

	//通过书名搜索图书
	@Override
	public String searchBooksWithTitle(String bookName) {
		return tool.searchBooksWithTitle(bookName);
	}
	
	//通过作者搜索图书
	@Override
	public String searchBooksWithAuthor(String author) {
		return tool.searchBooksWithAuthor(author);
	}

	//获取用户信息
	@Override
	public String getUserModel(String schoolId) {
		return tool.getUserModel(schoolId);
	}
	
	//获取我的借阅信息
	@Override
	public String[] getMyBorrowBooksList(String userid) {
		return tool.getMyBorrowModelList(userid);
	}

	//通过ISBN搜索图书
	@Override
	public String searchBooksWithISBN(String isbn13) {
		return tool.searchBooksWithISBN(isbn13);
	}
    
	//获取我的收藏信息
	@Override
	public String getMyFavBooksList(String userId) {
		return tool.getMyFavModelList(userId);
	}
	
    //续借图书
	@Override
	public String renew(String bookId, String time,String userId) {
		return tool.renew(userId,bookId,time);
	}
 
	//收藏图书
	@Override
	public String addMyFav(String bookId, String userId) {
		return tool.addMyFav(userId,bookId);
	}

	//取消收藏
	@Override
	public String deleteMyFav(String userId, String bookId) {
		return tool.deleteMyFav(userId,bookId);
	}

	//获取热门图书
	@Override
	public String getRankBooks() {
		return tool.getRankBooks();
	}

	//获取图书馆动态
	@Override
	public String getLibraryBoard() {
		return tool.getLibraryBoard();
	}

}
