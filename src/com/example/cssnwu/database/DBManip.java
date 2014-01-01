package com.example.cssnwu.database;

import java.sql.*;

public class DBManip {
	//jdbc驱动的位置
	private static String driver = "com.mysql.jdbc.Driver";
	//测试用数据库“myfirstdbexample”在网络上的位置
	private static String url = "jdbc:mysql://localhost:3306/courseselectsystem2" +
			"?useUnicode=true&characterEncoding=utf8";
	//用户名和密码
	private static String user = "root";
	private static String password = "";
	//连接句柄
	public static Connection conn = null;

	//建立连接
	public static void connect(){
		//url = "jdbc:mysql://localhost:3306/"+database;
		
		//加载数据库JDBC驱动
		try{
			Class.forName(driver);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		//利用给定的数据库位置，用户名和密码连接数据库
		try{
			conn = DriverManager.getConnection(url, user, password);
			if(!conn.isClosed()){
				System.out.println("成功连接数据库~");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConn(){
		if(conn == null){
			connect();
		}
		return conn;
	}
	//关闭连接
	public static void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		try {
			if(conn.isClosed()){
				System.out.println("关闭到数据库的连接~");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
