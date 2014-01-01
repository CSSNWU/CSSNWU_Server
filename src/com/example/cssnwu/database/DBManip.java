package com.example.cssnwu.database;

import java.sql.*;

public class DBManip {
	//jdbc������λ��
	private static String driver = "com.mysql.jdbc.Driver";
	//���������ݿ⡰myfirstdbexample���������ϵ�λ��
	private static String url = "jdbc:mysql://localhost:3306/courseselectsystem2" +
			"?useUnicode=true&characterEncoding=utf8";
	//�û���������
	private static String user = "root";
	private static String password = "";
	//���Ӿ��
	public static Connection conn = null;

	//��������
	public static void connect(){
		//url = "jdbc:mysql://localhost:3306/"+database;
		
		//�������ݿ�JDBC����
		try{
			Class.forName(driver);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		//���ø��������ݿ�λ�ã��û����������������ݿ�
		try{
			conn = DriverManager.getConnection(url, user, password);
			if(!conn.isClosed()){
				System.out.println("�ɹ��������ݿ�~");
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
	//�ر�����
	public static void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		try {
			if(conn.isClosed()){
				System.out.println("�رյ����ݿ������~");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
