/**
 * @(#)UserDatabaseService.java     	2013-10-8 下午3:00:14
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.database;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.cssnwu.businesslogicservice.resultenum.SYSTEM_STATE;
import com.example.cssnwu.businesslogicservice.resultenum.UserType;
import com.example.cssnwu.databaseservice.DatabaseService;
import com.example.cssnwu.po.PO;
import com.example.cssnwu.po.StudentPO;
import com.example.cssnwu.po.TeacherPO;
import com.example.cssnwu.po.UserPO;

/**
 *Class <code>UserDatabaseService.java</code> TODO
 *
 * @author never
 * @version 2013-10-8
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class UserDatabaseService extends UnicastRemoteObject implements DatabaseService{
    
	
	
	/**构造方法
	 * @throws RemoteException
	 */
	protected UserDatabaseService() throws RemoteException {
		super();
	}

	/* (non-Javadoc)
	 * Title: find
	 * Description:通过id查询UserPO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(int)
	 */
	@Override
	public PO find(int id) throws RemoteException {
		PO ret = null;
		DBManip.connect();
		// TODO 此处添加数据库操作的代码 
		try{
			Statement stmt = DBManip.getConn().createStatement();

			String sql1 = "select * from student where sno = '"+id +"'";
			ResultSet rs1 = stmt.executeQuery(sql1);
			System.out.println("look for student ");
			while(rs1.next()){
				System.out.println("student exist");
				if(rs1.getString("isLogin").equals("1")){
					ret = new  StudentPO(Integer.parseInt(rs1.getString("sno")),
							rs1.getString("name"),
							rs1.getString("password"),
							true,
							UserType.Student
							);
					break;
				}
				if(rs1.getString("isLogin").equals("0")){
					System.out.println(rs1.getString("password"));
					ret = new  StudentPO(Integer.parseInt(rs1.getString("sno")),
							rs1.getString("name"),
							rs1.getString("password"),
							false,
							UserType.Student
							);
					System.out.println(rs1.getString("password"));
					break;
				}
			}
			
			String sql2 = "select * from teacher where tno = '"+id+"'";
			ResultSet rs2 = stmt.executeQuery(sql2);
			System.out.println("look for teacher");
			while(rs2.next()){
				System.out.println("teacher exist");
				if(rs2.getString("isLogin").equals("1")){
					ret = new  TeacherPO(
							rs2.getString("name"),
							rs2.getString("password"),
							true,
							UserType.Teacher
							);
					break;
				}
				if(rs2.getString("isLogin").equals("0")){
					ret = new  TeacherPO(
							rs2.getString("name"),
							rs2.getString("password"),
							false,
							UserType.Teacher
							);
					break;
				}
			}
						
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		
		System.out.println(((UserPO)ret).getPassword());
		System.out.println(((UserPO)ret).isLogin());
		return ret;
	}
	/* (non-Javadoc)
	 * Title: find
	 * Description:通过关键字查询UserPO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(java.lang.String)
	 */
	@Override
	public ArrayList<PO> find(String key) throws RemoteException {
		// TODO 此处添加数据库操作的代码   key可以是院系或是其他
		return null;
	}

	/* (non-Javadoc)
	 * Title: findAll
	 * Description:查询所有UserPO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#findAll()
	 */
	@Override
	public ArrayList<PO> findAll() throws RemoteException {
		// TODO 此处添加数据库操作的代码 
		return null;
	}

	/* (non-Javadoc)
	 * Title: findAll
	 * Description:
	 * @see com.example.cssnwu.databaseservice.DatabaseService#findAll(int)
	 */
	@Override
	public ArrayList<PO> findAll(int id) throws RemoteException {
		return null;
	}

	/* (non-Javadoc)
	 * Title: update
	 * Description:更新PO对象
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean update(PO po) throws RemoteException {
		// TODO 此处添加数据库操作的代码 
		DBManip.connect();
//		try{
//			Statement stmt = DBManip.getConn().createStatement();
//			String sql = 
//		}
		return false;
	}

	/* (non-Javadoc)
	 * Title: update
	 * Description:更新PO对象的某个属性值
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean update(PO po, String attr, Object value)
			throws RemoteException {
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();

			String sql = "update student set " + attr +" = '"+ (String)value+
					"' where sno = '"+po.getId()+"'";
			stmt = DBManip.getConn().createStatement();
			stmt.execute(sql);
			
			sql = "update teacher set " + attr +" = '"+ (String)value+
					"' where tno = '"+po.getId()+"'";
			stmt = DBManip.getConn().createStatement();
			stmt.execute(sql);
			
			return true;

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBManip.close();
		}
		
		
		return false;
	}

	/* (non-Javadoc)
	 * Title: update
	 * Description:
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(int, java.util.HashMap)
	 */
	@Override
	public boolean update(int id, HashMap<Integer, Double> map)
			throws RemoteException {
		return false;
	}

	/* (non-Javadoc)
	 * Title: insert
	 * Description:插入UserPO对象
	 * @see com.example.cssnwu.databaseservice.DatabaseService#insert(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean insert(PO po) throws RemoteException {
		// TODO 此处添加数据库操作的代码 
		return false;
	}

	/* (non-Javadoc)
	 * Title: delete
	 * Description:删除PO对象
	 * @see com.example.cssnwu.databaseservice.DatabaseService#delete(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean delete(PO po) throws RemoteException {
		return false;
	}

	/* (non-Javadoc)
	 * Title: load
	 * Description:加载数据
	 * @see com.example.cssnwu.databaseservice.DatabaseService#load()
	 */
	@Override
	public void load() throws RemoteException {
		
	}

	/* (non-Javadoc)
	 * Title: save
	 * Description:保存数据
	 * @see com.example.cssnwu.databaseservice.DatabaseService#save()
	 */
	@Override
	public void save() throws RemoteException {
		
	}

	/* (non-Javadoc)
	 * Title: init
	 * Description:初始化数据
	 * @see com.example.cssnwu.databaseservice.DatabaseService#init()
	 */
	@Override
	public void init() throws RemoteException {
		
	}

	/* (non-Javadoc)
	 * Title: finish
	 * Description:结束对数据的操作
	 * @see com.example.cssnwu.databaseservice.DatabaseService#finish()
	 */
	@Override
	public void finish() throws RemoteException {
	}
	
	
	@Override
	public boolean checkSystemState(SYSTEM_STATE system_state) throws RemoteException {
		// TODO Auto-generated method stub
		boolean systemStateChangeable = false;
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "select * from systemState where systemFunction = '"+system_state+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				if(rs.getInt("state")==1){
					systemStateChangeable = true;
				}else if(rs.getInt("state")==0){
					systemStateChangeable = false;
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		DBManip.close();
		return systemStateChangeable;
	}

	public boolean setSystemState(SYSTEM_STATE systemState,boolean wantThisFunctionUsable) throws RemoteException {
		boolean updateSystemStateSuccess = false;
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql;  
			if(wantThisFunctionUsable){
				sql = "update systemstate set state = '1' where systemFunction = '"+systemState+"'"; 
			}else {
				sql = "update systemstate set state = '0' where systemFunction = '"+systemState+"'"; 
			}
			stmt.execute(sql);
			updateSystemStateSuccess = true;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("捕捉到SQLException！！！");
		}
		
		DBManip.close();
		return updateSystemStateSuccess;
	}

	public static void main(String[] args) throws RemoteException{
		UserDatabaseService uds = new UserDatabaseService();
		StudentPO spo = new StudentPO(111160126, "zhuyuanfu", "1", false, UserType.Student);
		System.out.println(uds.update(spo, "password", "123456"));
		
		uds.setSystemState(SYSTEM_STATE.dropCourse, true);
	}

	
}
