/**
 * @(#)SchoolStrategyDatabaseService.java     	2013-10-10 下午8:44:28
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.database;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


import com.example.cssnwu.businesslogicservice.resultenum.SYSTEM_STATE;
import com.example.cssnwu.databaseservice.DatabaseService;
import com.example.cssnwu.po.PO;
import com.example.cssnwu.po.SchoolStrategyPO;

/**
 *Class <code>SchoolStrategyDatabaseService.java</code> SchoolStrategy对象的数据处理类
 *
 * @author never
 * @version 2013-10-10
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class SchoolStrategyDatabaseService extends UnicastRemoteObject implements DatabaseService{

	/**构造方法
	 * @throws RemoteException
	 */
	protected SchoolStrategyDatabaseService() throws RemoteException {
		super();
	}

	/* (non-Javadoc)
	 * Title: find
	 * Description: 通过id查询PO对象
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(int)
	 */
	@Override
	public PO find(int id) throws RemoteException {
		SchoolStrategyPO ret = null;
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "select * from schoolPlan where year = '"+id+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				ret = new SchoolStrategyPO();
				ret.setId(id);
				int[] minCreditOfYear = new int[4];
				minCreditOfYear[0] = rs.getInt("creditRequirementOfYear1");
				minCreditOfYear[1] = rs.getInt("creditRequirementOfYear2");
				minCreditOfYear[2] = rs.getInt("creditRequirementOfYear3");
				minCreditOfYear[3] = rs.getInt("creditRequirementOfYear4");
				ret.setMinCreditPerSeason(minCreditOfYear);
				int totalCredit = minCreditOfYear[0]+minCreditOfYear[1]+
									minCreditOfYear[2]+minCreditOfYear[3];
				ret.setTotalCredit(totalCredit);
				
				
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		DBManip.close();
		return ret;
	}

	/* (non-Javadoc)
	 * Title: find
	 * Description:通过学期查询不同学期或是年份的DepartmentPlanPO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(java.lang.String)
	 */
	@Override
	public ArrayList<PO> find(String key) throws RemoteException {
		// TODO 此处添加数据库操作的代码   key指代年份或是学期
		return null;
	}

	/* (non-Javadoc)
	 * Title: findAll
	 * Description:查询所有的DepartmentPlanPO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#findAll()
	 */
	@Override
	public ArrayList<PO> findAll() throws RemoteException {
		DBManip.connect();
		ArrayList<PO> ret = new ArrayList<PO>();
		
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "select * from schoolPlan";
			ResultSet rs = stmt.executeQuery(sql); 
			while(rs.next()){
				SchoolStrategyPO tempSchoolStrategyPO = new SchoolStrategyPO();
				//年份
				tempSchoolStrategyPO.setId(rs.getInt("Year"));
				//每学年学分数
				int[] minCreditOfYear = new int[4];
				minCreditOfYear[0] = rs.getInt("creditRequirementOfYear1");
				minCreditOfYear[1] = rs.getInt("creditRequirementOfYear2");
				minCreditOfYear[2] = rs.getInt("creditRequirementOfYear3");
				minCreditOfYear[3] = rs.getInt("creditRequirementOfYear4");
				//四年下来总学分数
				tempSchoolStrategyPO.setMinCreditPerSeason(minCreditOfYear);
				int totalCredit = minCreditOfYear[0]+minCreditOfYear[1]+
						minCreditOfYear[2]+minCreditOfYear[3];
				tempSchoolStrategyPO.setTotalCredit(totalCredit);
				
				ret.add(tempSchoolStrategyPO);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return ret;
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
		if(po!=null){
			try{
				Statement stmt = DBManip.getConn().createStatement();
				String sql = "update schoolplan set creditRequirementOfYear1 = '"+((SchoolStrategyPO)po).getMinCreditPerSeason()[0]+"',"
						+" creditRequirementOfYear2 = '"+((SchoolStrategyPO)po).getMinCreditPerSeason()[1]+"',"
						+" creditRequirementOfYear3 = '"+((SchoolStrategyPO)po).getMinCreditPerSeason()[1]+"',"
						+" creditRequirementOfYear4 = '"+((SchoolStrategyPO)po).getMinCreditPerSeason()[1]+"'"
						+" where year = "+((SchoolStrategyPO)po).getId();
				stmt.execute(sql);
				return true;
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		DBManip.close();
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
		// TODO 此处添加数据库操作的代码 
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
	 * Description:插入SchoolStrategyPO对象
	 * @see com.example.cssnwu.databaseservice.DatabaseService#insert(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean insert(PO po) throws RemoteException {
		// TODO 此处添加数据库操作的代码 
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "select * from schoolPlan where year  = '"+((SchoolStrategyPO)po).getId()+"'";
			ResultSet rs  = stmt.executeQuery(sql);
			if(rs.next()){
				System.out.println("数据库里已经有了这个全校计划，请修改这条计划，或者删除这条计划后进行插入操作");
				return false;
			}else{
				System.out.println("可以插入该全校计划");
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "insert into schoolPlan ( year,creditRequirementOfYear1," +
						"creditRequirementOfYear2,creditRequirementOfYear3,creditRequirementOfYear4 )" +
						" values ( '"
						+((SchoolStrategyPO)po).getId()+"','"+
						((SchoolStrategyPO)po).getMinCreditPerSeason()[0]+"','"+
						((SchoolStrategyPO)po).getMinCreditPerSeason()[1]+"','"+
						((SchoolStrategyPO)po).getMinCreditPerSeason()[2]+"','"+
						((SchoolStrategyPO)po).getMinCreditPerSeason()[3]+"')";
				
				tempSTMT1.execute(tempSQL1);
				return true;
				
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		DBManip.close();
		return false;
	}

	/* (non-Javadoc)
	 * Title: delete
	 * Description:删除PO对象
	 * @see com.example.cssnwu.databaseservice.DatabaseService#delete(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean delete(PO po) throws RemoteException {
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "delete from schoolPlan where year = '"+((SchoolStrategyPO)po).getId()+"'";
			stmt.execute(sql);
			System.out.println("现在数据库里不存在年份为"+((SchoolStrategyPO)po).getId()+"的记录了~");
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
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
	public boolean checkSystemState(SYSTEM_STATE system_state)throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean setSystemState(SYSTEM_STATE systemState,boolean isThisPersonLoginNow)
			throws RemoteException{
		return false;
	}

}
