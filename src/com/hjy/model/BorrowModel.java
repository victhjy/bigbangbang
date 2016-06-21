package com.hjy.model;

public class BorrowModel {

	private String borrowId;
	private String userId;
	private String bookId;
	private String shouldReturn;
	private String renew;
	
	private int userIdInt;
	private int bookIdInt;
	public int getUserIdInt() {
		return userIdInt;
	}
	public void setUserIdInt(int userIdInt) {
		this.userIdInt = userIdInt;
	}
	public int getBookIdInt() {
		return bookIdInt;
	}
	public void setBookIdInt(int bookIdInt) {
		this.bookIdInt = bookIdInt;
	}
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getShouldReturn() {
		return shouldReturn;
	}
	public void setShouldReturn(String shouldReturn) {
		this.shouldReturn = shouldReturn;
	}
	public String getRenew() {
		return renew;
	}
	public void setRenew(String renew) {
		this.renew = renew;
	}
}
