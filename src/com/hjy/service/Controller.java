package com.hjy.service;

import java.io.IOException;
import java.io.Reader;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjy.model.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Controller {

	private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader; 
    private BooksModel model=new BooksModel();

    static{
        try{
            reader    = Resources.getResourceAsReader("Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String decode(String str) {     	
        byte[] bt = null; 
        String ret = null;
        try {  
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
        bt = decoder.decodeBuffer(str);  
        ret = new String(bt, "UTF-8");
        } catch (IOException e) {  
        e.printStackTrace();  
        }  
         
        return ret;  
    }  
    
    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }
    SqlSession session = sqlSessionFactory.openSession();
    
    public String postBooksInfo(BooksModel model){
    	SqlSession session = sqlSessionFactory.openSession();       
        try{
            BooksModelInterface testOperation=session.getMapper(BooksModelInterface.class);
              testOperation.postBooksInfo(model);
              return "aaaa";
              
        } finally {
            session.close();	
      }
    }
      
    public static void main(String[] argvs){
    	Controller tool=new Controller();
    	    	
    }
    public  String getAllBooksInfo(){
    	SqlSession session = sqlSessionFactory.openSession();       
        try{
            BooksModelInterface testOperation=session.getMapper(BooksModelInterface.class);
            List<BooksModel> resultList=testOperation.getBooksModelList();
            Gson gson=new Gson();
            String str=gson.toJson(resultList);
            return str;
        } finally {
            session.close();	
      }
    }
    
    public String loginIn(String id,String password){
    	SqlSession session = sqlSessionFactory.openSession();       
        try{        	
            LoginInInterface testOperation=session.getMapper(LoginInInterface.class);           
              LoginIn model=testOperation.loginIn(id);              
              System.out.println(testOperation.loginIn(id).getUname());
              if(model.getPassword().equals(password)){
            	  Gson gson=new Gson();
            	  String result=gson.toJson(model);
            	  return result;           	  
              }
              else if(model.getPassword() == ""){
            	  return null;
              }
              else{
            	  System.out.println("0");
            	  return null;
              }
            
        } finally {
            session.close();	
      }
    }
    
    public String searchBooksWithTitle(String title){
    	SqlSession session = sqlSessionFactory.openSession();       	
    	String titleUTF8 = this.decode(title);
        try{
//        	this.charSetConvert(title);
//        	System.out.println("获取编码后的字符串"+this.charSetConvert(title));
        	byte[] bytes=title.getBytes();
            BooksModelInterface testOperation=session.getMapper(BooksModelInterface.class);
              List<BooksModel> list=testOperation.searchBooksWithTitle(titleUTF8);
             Gson gson=new Gson();
             String str=gson.toJson(list);
             System.out.println("通过书名搜索图书-->"+titleUTF8);
             return str;         
		} finally {
            session.close();	
      }
       
    }
    
    public String searchBooksWithAuthor(String author){
    	String authorUTF8 = this.decode(author);
    	SqlSession session = sqlSessionFactory.openSession();       
        try{
            BooksModelInterface testOperation=session.getMapper(BooksModelInterface.class);
              List<BooksModel> list=testOperation.searchBooksWithAuthor(authorUTF8);
             Gson gson=new Gson();
             String str=gson.toJson(list);
             System.out.println("通过作者搜索图书-->"+authorUTF8);
             return str;
            
        } finally {
            session.close();	
      }
    }
    
    public String searchBooksWithISBN(String isbn13){
    	SqlSession session = sqlSessionFactory.openSession();       
        try{
            BooksModelInterface testOperation=session.getMapper(BooksModelInterface.class);
              List<BooksModel> list=testOperation.searchBooksWithISBN(isbn13);
             Gson gson=new Gson();
             String str=gson.toJson(list);
             System.out.println("通过ISBN搜索图书-->");
             return str;
            
        } finally {
            session.close();	
      }
    }   
    
    public String getUserModel(String schoolId){
    	SqlSession session = sqlSessionFactory.openSession();       
        try{
        	LoginInInterface testOperation=session.getMapper(LoginInInterface.class);           
            LoginIn model=testOperation.loginIn(schoolId);                               
          	  Gson gson=new Gson();
          	  String result=gson.toJson(model);
          	  return result;           	  
            }
        finally {
            session.close();	
      }
    }
    //只获取usermodel
    public LoginIn getUserModelOnly(String schoolId){
    	SqlSession session = sqlSessionFactory.openSession();       
        try{
        	LoginInInterface testOperation=session.getMapper(LoginInInterface.class);           
            LoginIn model=testOperation.loginIn(schoolId);    
//            System.out.println("getModelOnly   "+schoolId);
//          	  Gson gson=new Gson();
//          	  String result=gson.toJson(model);
          	  return model;           	  
            }
        finally {
            session.close();	
      }
    }
    
    //借书信息主函数
    public String[] getMyBorrowModelList(String userId){  
    	List<BorrowModel> myBorrowModelList;
    	List<BooksModel> myBorrowBooksList;
    	SqlSession session = sqlSessionFactory.openSession();       
        try{
        	BooksModelInterface testOperation11111111=session.getMapper(BooksModelInterface.class);           
        	myBorrowBooksList=testOperation11111111.getMyBorrowBooks(this.getListBorrow(userId)); 
        	
          	  Gson gson=new Gson();        	  
          	  String result=gson.toJson(myBorrowBooksList);
          	  String[] arr={result,this.getMyBorrowBooksRenew(userId)};
          	 System.out.println("获取借阅列表");
          	  return arr;
            }
        finally {
            session.close();	
      }      
    }
    public List<String> getListBorrow(String userId){
    	List<BorrowModel> myBorrowModelList;
    	SqlSession session = sqlSessionFactory.openSession();
    	try{
        	BorrowModelInterface testOperation=session.getMapper(BorrowModelInterface.class);           
            myBorrowModelList=testOperation.getMyBorrowList(Integer.toString(this.getUserModelOnly(userId).getUid())); 
            List<String> bookIdList =new ArrayList<>();
        	for(int i=0;i<myBorrowModelList.size();i++){
        		bookIdList.add(myBorrowModelList.get(i).getBookId());     		
        	}
        	return bookIdList;
       }
    	finally {
            session.close();	
      }  
    }
    
    public String getMyFavModelList(String userId){
    	List<BooksModel> myFavBooksList;
    	SqlSession session = sqlSessionFactory.openSession();       
        try{      	
        	BooksModelInterface testOperation11111111=session.getMapper(BooksModelInterface.class);           
        	myFavBooksList=testOperation11111111.getMyBorrowBooks(this.getListFav(userId));       
          	  Gson gson=new Gson();        	  
          	  String result=gson.toJson(myFavBooksList);
          	 System.out.println("获取收藏列表");
          	  return result;
            }
        finally {
            session.close();	
      }      
    }
    
    public List<String> getListFav(String userId){
    	List<MyFavBooks> myFavModelList;
    	SqlSession session = sqlSessionFactory.openSession();
    	try{
        	MyFavBooksInterface testOperation=session.getMapper(MyFavBooksInterface.class);           
        	myFavModelList=testOperation.getMyFavList(Integer.toString(this.getUserModelOnly(userId).getUid())); 
            List<String> bookIdList =new ArrayList<>();
        	for(int i=0;i<myFavModelList.size();i++){
        		bookIdList.add(myFavModelList.get(i).getBookId());     		
        	}        
        	return bookIdList;
       }
    	finally {
            session.close();	
      }  
    }
    
    public String getMyBorrowBooksRenew(String userId){
    	List<BorrowModel> myBorrowModelList;
    	SqlSession session = sqlSessionFactory.openSession();
    	try{
        	BorrowModelInterface testOperation=session.getMapper(BorrowModelInterface.class);           
        	myBorrowModelList=testOperation.getMyBorrowList(Integer.toString(this.getUserModelOnly(userId).getUid())); 
        	Gson gson=new Gson();
        	String result=gson.toJson(myBorrowModelList);
        	 System.out.println("获取借阅列表");
        	return result;
       }
    	finally {
            session.close();	
      }  
    }
    
    public String renew(String userId,String bookId,String time){
    	SqlSession session = sqlSessionFactory.openSession();
    	try{
        	BorrowModelInterface testOperation=session.getMapper(BorrowModelInterface.class);   
        	BorrowModel model=new BorrowModel();
//        	model.setUserIdInt(Integer.valueOf(userId));
//        	model.setBookIdInt(Integer.valueOf(bookId));
        	model.setBookId(bookId);
        	model.setUserId(String.valueOf(this.getUserModelOnly(userId).getUid()));
        	model.setShouldReturn(time);
        	System.out.println("用户 "+userId+"续借了 "+bookId);
        	int a=testOperation.renew(model);
//        	int a=testOperation.renew(userId,bookId,time);
        	session.commit();
        	return " "+a;
       }
    	finally {
            session.close();	
      }  
    }
    
    //收藏
    public String addMyFav(String userId,String bookId){
    	SqlSession session = sqlSessionFactory.openSession();
    	try{
    		System.out.println("用户 "+userId+"收藏了图书 "+bookId);
        	MyFavBooksInterface testOperation=session.getMapper(MyFavBooksInterface.class);  
        	MyFavBooks model=new MyFavBooks();
            model.setBookId(bookId);
            model.setUserId(Integer.toString(this.getUserModelOnly(userId).getUid()));
    
        	int a=testOperation.addMyFav(model);
        	session.commit();       	
        	return " "+a;
       }
    	finally {
            session.close();	
      }  
    }
    //删除收藏
    public String deleteMyFav(String bookId,String userId){
    	SqlSession session = sqlSessionFactory.openSession();
    	try{
        	MyFavBooksInterface testOperation=session.getMapper(MyFavBooksInterface.class);  
        	MyFavBooks model=new MyFavBooks();
            model.setUserId(Integer.toString(this.getUserModelOnly(userId).getUid()));
            model.setBookId(bookId);
            System.out.println("用户 "+userId+"取消收藏了 "+bookId);
       
        	int a=testOperation.deleteMyFav(model);
      
        	session.commit();
        	return " "+a;
       }
    	finally {
            session.close();	
      }  
    }
    
    public String getRankBooks(){
    	SqlSession session = sqlSessionFactory.openSession();
    	try{
        	BooksModelInterface testOperation=session.getMapper(BooksModelInterface.class);  
        	List<BooksModel> list=testOperation.getRankBooks();
            Gson gson=new Gson();
            String res=gson.toJson(list);
            System.out.println("获取热门图书");
        	return res;
       }
    	finally {
            session.close();	
      }  
    }
    
    public String getLibraryBoard(){
    	SqlSession session = sqlSessionFactory.openSession();
    	try{
        	LibraryBoardInterface testOperation=session.getMapper(LibraryBoardInterface.class);  
        	List<LibraryBoard> list=testOperation.getAll();
            Gson gson=new Gson();
            String res=gson.toJson(list);
            System.out.println("获取图书馆公告");
        	return res;
       }
    	finally {
            session.close();	
      }  
    }
}
