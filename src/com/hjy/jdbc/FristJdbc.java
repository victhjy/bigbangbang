package com.hjy.jdbc;
import java.sql.*;
public class FristJdbc {
	public void addBooksInfo(String bookName,String author,String press,String content,String price,
			String count){
		String sql1="insert into bookinfo values(";
		String dot1="','";
		String dot2="');";
		String dot3="'";
		String dot4=",'";
		String bid="null";
		String sql=sql1+bid+dot4+bookName+dot1+author+dot1+press+dot1+content+dot1+price+dot1+count+dot2;
		System.out.println(sql);
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/mydb", "root","");
		    st=con.createStatement();
		    rs=st.executeQuery(sql);
//		    while(rs){
////		    	System.out.println(rs.getInt("age"));
////		    	System.out.println(rs.getString("name"));
//		    }
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
    finally
	{
		 try
         {
              con.close();
         }catch(Exception e)
         {}
        
         try
         {
              st.close();
         }catch(Exception e)
         {
         }
        
         try
         {
              rs.close();
         }catch(Exception e)
         {
         }
	}
	}
    public String getName(String age){
    	String sql1="select name1 from t1 where age=";
    	String sql=sql1+age;
    	
    	String name=null;
    	Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/mydb", "root","");
		    st=con.createStatement();
		    rs=st.executeQuery(sql);
		    while(rs.next()){
		    	System.out.println(rs.getString("name1")+"!!!!!!!!!!!!!!");
		    	name=rs.getString("name1");
		    }
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
    finally
	{
		 try
         {
              con.close();
         }catch(Exception e)
         {}
        
         try
         {
              st.close();
         }catch(Exception e)
         {
         }
        
         try
         {
              rs.close();
         }catch(Exception e)
         {
         }
	}
		return name;
    }
    public String getTest(int a){
    	return "111111";
    }
	public static void main(String[] args){
		FristJdbc jdbc=new FristJdbc();
		System.out.println("========="+jdbc.getName("20")+"=====");
		
		String sql="select * from t1";
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/mydb", "root","");
		    st=con.createStatement();
		    rs=st.executeQuery(sql);
		    while(rs.next()){
		    	System.out.println(rs.getInt("age"));
		    	System.out.println(rs.getString("name1"));
		    }
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
    finally
	{
		 try
         {
              con.close();
         }catch(Exception e)
         {}
        
         try
         {
              st.close();
         }catch(Exception e)
         {
         }
        
         try
         {
              rs.close();
         }catch(Exception e)
         {
         }
	}
}
}
