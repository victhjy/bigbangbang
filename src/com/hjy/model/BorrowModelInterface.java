package com.hjy.model;

import java.util.List;

public interface BorrowModelInterface {
	public List<BorrowModel> getMyBorrowList(String userId);
	public int renew(BorrowModel model);
//	public int renew(String userId,String bookId,String shouldReturn);
}
