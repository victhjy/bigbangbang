package com.hjy.model;

import java.util.List;

public interface MyFavBooksInterface {

	public List<MyFavBooks> getMyFavList(String userId);
//	public int addMyFav(String userId,String bookId);
	public int addMyFav(MyFavBooks model);
	public int deleteMyFav(MyFavBooks model);
}
