package com.hjy.model;

import java.util.List;

public interface BooksModelInterface {
    public String postBooksInfo(BooksModel model);
	public List<BooksModel> getBooksModelList();
	public List<BooksModel> searchBooksWithTitle(String title);
	public List<BooksModel> searchBooksWithAuthor(String author);
	public List<BooksModel> searchBooksWithISBN(String isbn13);
	public List<BooksModel> getRankBooks();
	public List<String> getMyBorrowBooksId(String userId);
    public List<BooksModel> getMyBorrowBooks(List<String> list);
}
