/**
 * @(#)SchoolStrategyDatabaseService.java     	2013-10-10 ����8:44:28
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
 *Class <code>SchoolStrategyDatabaseService.java</code> SchoolStrategy��������ݴ�����
 *
 * @author never
 * @version 2013-10-10
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class SchoolStrategyDatabaseService extends UnicastRemoteObject implements DatabaseService{

	/**���췽��
	 * @throws RemoteException
	 */
	protected SchoolStrategyDatabaseService() throws RemoteException {
		super();
	}

	/* (non-Javadoc)
	 * Title: find
	 * Description: ͨ��id��ѯPO����
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
	 * Description:ͨ��ѧ�ڲ�ѯ��ͬѧ�ڻ�����ݵ�DepartmentPlanPO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(java.lang.String)
	 */
	@Override
	public ArrayList<PO> find(String key) throws RemoteException {
		// TODO �˴�������ݿ�����Ĵ���   keyָ����ݻ���ѧ��
		return null;
	}

	/* (non-Javadoc)
	 * Title: findAll
	 * Description:��ѯ���е�DepartmentPlanPO
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
				//���
				tempSchoolStrategyPO.setId(rs.getInt("Year"));
				//ÿѧ��ѧ����
				int[] minCreditOfYear = new int[4];
				minCreditOfYear[0] = rs.getInt("creditRequirementOfYear1");
				minCreditOfYear[1] = rs.getInt("creditRequirementOfYear2");
				minCreditOfYear[2] = rs.getInt("creditRequirementOfYear3");
				minCreditOfYear[3] = rs.getInt("creditRequirementOfYear4");
				//����������ѧ����
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
	 * Description:����PO����
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean update(PO po) throws RemoteException {
		// TODO �˴�������ݿ�����Ĵ��� 
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
	 * Description:����PO�����ĳ������ֵ
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean update(PO po, String attr, Object value)
			throws RemoteException {
		// TODO �˴�������ݿ�����Ĵ��� 
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
	 * Description:����SchoolStrategyPO����
	 * @see com.example.cssnwu.databaseservice.DatabaseService#insert(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean insert(PO po) throws RemoteException {
		// TODO �˴�������ݿ�����Ĵ��� 
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "select * from schoolPlan where year  = '"+((SchoolStrategyPO)po).getId()+"'";
			ResultSet rs  = stmt.executeQuery(sql);
			if(rs.next()){
				System.out.println("���ݿ����Ѿ��������ȫУ�ƻ������޸������ƻ�������ɾ�������ƻ�����в������");
				return false;
			}else{
				System.out.println("���Բ����ȫУ�ƻ�");
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
	 * Description:ɾ��PO����
	 * @see com.example.cssnwu.databaseservice.DatabaseService#delete(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean delete(PO po) throws RemoteException {
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "delete from schoolPlan where year = '"+((SchoolStrategyPO)po).getId()+"'";
			stmt.execute(sql);
			System.out.println("�������ݿ��ﲻ�������Ϊ"+((SchoolStrategyPO)po).getId()+"�ļ�¼��~");
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return false;
	}

	/* (non-Javadoc)
	 * Title: load
	 * Description:��������
	 * @see com.example.cssnwu.databaseservice.DatabaseService#load()
	 */
	@Override
	public void load() throws RemoteException {
		
	}

	/* (non-Javadoc)
	 * Title: save
	 * Description:��������
	 * @see com.example.cssnwu.databaseservice.DatabaseService#save()
	 */
	@Override
	public void save() throws RemoteException {
		
	}

	/* (non-Javadoc)
	 * Title: init
	 * Description:��ʼ������
	 * @see com.example.cssnwu.databaseservice.DatabaseService#init()
	 */
	@Override
	public void init() throws RemoteException {
		
	}

	/* (non-Javadoc)
	 * Title: finish
	 * Description:���������ݵĲ���
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
