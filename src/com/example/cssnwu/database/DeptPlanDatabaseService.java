/**
 * @(#)DeptPlanDatabaseService.java     	2013-10-10 下午8:44:13
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

import com.example.cssnwu.businesslogicservice.resultenum.CourseType;
import com.example.cssnwu.businesslogicservice.resultenum.Department;
import com.example.cssnwu.businesslogicservice.resultenum.SYSTEM_STATE;
import com.example.cssnwu.databaseservice.DatabaseService;
import com.example.cssnwu.po.CoursePO;
import com.example.cssnwu.po.DepartmentPlanPO;
import com.example.cssnwu.po.PO;

/**
 *Class <code>DeptPlanDatabaseService.java</code> 和DepartmentPlan对象有关的数据操作类
 *
 * @author never
 * @version 2013-10-10
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class DeptPlanDatabaseService extends UnicastRemoteObject implements DatabaseService {

	/**构造方法
	 * @throws RemoteException
	 */
	protected DeptPlanDatabaseService() throws RemoteException {
		super();
	}

	/**
	 * Title: find
	 * Description:通过id查询DepartmentPlanPO
	 * @author zhuyuanfu
	 * @param int
	 * @return PO
	 * @throws RemoteException
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(int)
	 */
	@Override
	public PO find(int id) throws RemoteException {
		DepartmentPlanPO ret = null;
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "select * from deptPlan where DPID = "+id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				ret = new DepartmentPlanPO();
				((DepartmentPlanPO)ret).setDepartment(Department.valueOf(rs.getString("dept")));
			
				ArrayList<CoursePO> coursePOList = new ArrayList<CoursePO>();
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "select * from course where cno in "
						+"(select cno from deptPlan where id = "+id+")";
				ResultSet tempRS1 = tempSTMT1.executeQuery(tempSQL1);
				while(tempRS1.next()){
					CoursePO tempCoursePO = new CoursePO(tempRS1.getInt("cno"));
					tempCoursePO.setCourseIntro(tempRS1.getString("courseIntroduction"));
					tempCoursePO.setCourseLocation(tempRS1.getString("place"));
					tempCoursePO.setCourseName(tempRS1.getString("name"));
					tempCoursePO.setCourseTime(tempRS1.getString("time"));
					tempCoursePO.setCourseType(CourseType.valueOf(tempRS1.getString("courseType")));
					tempCoursePO.setCredit(tempRS1.getInt("credit"));
					tempCoursePO.setEstablishTime(tempRS1.getString("startTime"));
					
					ArrayList<String> teacherNameList = new ArrayList<String>();
					ArrayList<Integer> teacherIdList = new ArrayList<Integer>();
					
					Statement tempSTMT2 = DBManip.getConn().createStatement();
					String tempSQL2 = "select distinct tno,name from teacher where tno in "
							+"(select distinct tno from tc where cno = "
							+tempRS1.getString("cno")+" )";
					ResultSet tempRS2 = tempSTMT2.executeQuery(tempSQL2);
					while(tempRS2.next()){
						teacherNameList.add(tempRS2.getString("name"));
						teacherIdList.add(tempRS2.getInt("tno"));
					}
					
					tempCoursePO.setTeacherIdList(teacherIdList);
					tempCoursePO.setTeacherNameList(teacherNameList);
					
					coursePOList.add(tempCoursePO);
				}
				
				
				((DepartmentPlanPO)ret).setCourseList(coursePOList);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return ret;
	}
	


	/**
	 * Title: find
	 * Description:通过关键字（院系）查询DepartmentPlanPO
	 * @author zhuyuanfu
	 * @param String
	 * @return ArrayList<PO>
	 * @throws RemoteException
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(java.lang.String)
	 */
	@Override
	public ArrayList<PO> find(String key) throws RemoteException {
		ArrayList<PO> ret = new ArrayList<PO>();
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "select * from deptplan";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				if(rs.getString("dept").equals(key)){
					DepartmentPlanPO deptPO = new DepartmentPlanPO();
					deptPO.setDepartment(Department.valueOf(key));
					ArrayList<CoursePO> coursePOList = new ArrayList<CoursePO>();
					Statement tempSTMT1 = DBManip.getConn().createStatement();
					String tempSQL1 = "select * from course";
					ResultSet tempRS1 = tempSTMT1.executeQuery(tempSQL1);
					while(tempRS1.next()){
						if(tempRS1.getString("dept").equals(key)){
							CoursePO tempCoursePO = new CoursePO(tempRS1.getInt("cno"));
							tempCoursePO.setCourseName(tempRS1.getString("name"));
							tempCoursePO.setCourseLocation(tempRS1.getString("place"));
							tempCoursePO.setCourseTime(tempRS1.getString("time"));
							tempCoursePO.setCourseIntro(tempRS1.getString("courseIntroduction"));
							tempCoursePO.setCourseType(CourseType.valueOf(tempRS1.getString("dept")));
							tempCoursePO.setEstablishTime(tempRS1.getString("startTime"));
							tempCoursePO.setCredit(tempRS1.getInt("credit"));
							
							ArrayList<Integer> teacherIdList = new ArrayList<Integer>();
							ArrayList<String> teacherNameList = new ArrayList<String>();
							Statement tempSTMT2 = DBManip.getConn().createStatement();
							String tempSQL2 = "select tno,name from teacher where tno in"
									+"( select tno from course where cno = '"
									+tempCoursePO.getId()+"')";
							ResultSet tempRS2 = tempSTMT2.executeQuery(tempSQL2);
							while(tempRS2.next()){
								teacherIdList.add(tempRS2.getInt("tno"));
								teacherNameList.add(tempRS2.getString("name"));
							}
							tempCoursePO.setTeacherIdList(teacherIdList);
							tempCoursePO.setTeacherNameList(teacherNameList);
							coursePOList.add(tempCoursePO);
						}
					}
					deptPO.setCourseList(coursePOList);
					ret.add(deptPO);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		DBManip.close();
		return ret;
	}

	/**
	 * Title: findAll
	 * Description:查询所有的DepartmentPlanPO
	 * @author zhuyuanfu
	 * @param no param
	 * @return ArrayList<PO>
	 * @throws RemoteException
	 * @see com.example.cssnwu.databaseservice.DatabaseService#findAll()
	 */
	@Override
	public ArrayList<PO> findAll() throws RemoteException {
		ArrayList<PO> ret = new ArrayList<PO>();
		DBManip.connect();
		try{
			DepartmentPlanPO seDPO1 = new DepartmentPlanPO();
			DepartmentPlanPO geoDPO1 = new DepartmentPlanPO();
			DepartmentPlanPO evrDPO1 = new DepartmentPlanPO();
			DepartmentPlanPO businessDPO1 = new DepartmentPlanPO();
			DepartmentPlanPO infoManagementDPO1 = new DepartmentPlanPO();
			
			
			seDPO1.setDepartment(Department.软件学院);
			geoDPO1.setDepartment(Department.地理学院);
			infoManagementDPO1.setDepartment(Department.信管学院);
			businessDPO1.setDepartment(Department.商学院);
			evrDPO1.setDepartment(Department.环境学院);
			
			
			ArrayList<CoursePO> seCoursePOList = new ArrayList<CoursePO>();
			ArrayList<CoursePO> geoCoursePOList = new ArrayList<CoursePO>();
			ArrayList<CoursePO> infoManagementCoursePOList = new ArrayList<CoursePO>();
			ArrayList<CoursePO> businessCoursePOList = new ArrayList<CoursePO>();
			ArrayList<CoursePO> evrCoursePOList = new ArrayList<CoursePO>();
			
			
			
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "select * from deptplan";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				CoursePO tempCoursePO = new CoursePO(rs.getInt("cno"));
				
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "select * from course where cno = '"+tempCoursePO.getId()+"'";
				ResultSet tempRS1 = tempSTMT1.executeQuery(tempSQL1);
				if(tempRS1.next()){
					tempCoursePO.setCourseIntro(tempRS1.getString("courseIntroduction"));
					tempCoursePO.setCourseLocation(tempRS1.getString("place"));
					tempCoursePO.setCourseName(tempRS1.getString("name"));
					tempCoursePO.setCourseTime(tempRS1.getString("time"));
					tempCoursePO.setCourseType(CourseType.valueOf(tempRS1.getString("courseType")));
					tempCoursePO.setCredit(tempRS1.getInt("credit"));
					tempCoursePO.setEstablishTime(tempRS1.getString("startTime"));
					
					ArrayList<String> teacherNameList = new ArrayList<String>();
					ArrayList<Integer> teacherIdList = new ArrayList<Integer>();
					
					Statement tempSTMT2 = DBManip.getConn().createStatement();
					String tempSQL2 = "select distinct tno,name from teacher where tno in "
							+"(select tno from tc where cno = '"+tempCoursePO.getId()+"')";
					System.out.println(tempSQL2);
					ResultSet tempRS2 = tempSTMT2.executeQuery(tempSQL2);
					while(tempRS2.next()){
						teacherNameList.add(tempRS2.getString("name"));
						teacherIdList.add(tempRS2.getInt("tno"));
					}
					
					tempCoursePO.setTeacherNameList(teacherNameList);
					tempCoursePO.setTeacherIdList(teacherIdList);
				}
				
				if(rs.getString("dept").equals("软件学院")){
					seCoursePOList.add(tempCoursePO);
				}
				if(rs.getString("dept").equals("地理学院")){
					geoCoursePOList.add(tempCoursePO);
				}	
				if(rs.getString("dept").equals("环境学院")){
					evrCoursePOList.add(tempCoursePO);
				}	
				if(rs.getString("dept").equals("商学院")){
					businessCoursePOList.add(tempCoursePO);
				}	
				if(rs.getString("dept").equals("信管学院")){
					infoManagementCoursePOList.add(tempCoursePO);
				}	
				
				
				
				
			}
			
			
			seDPO1.setCourseList(seCoursePOList);
			geoDPO1.setCourseList(geoCoursePOList);
			
			
			//将学期学分要求写进 院系DPO1
			Statement tempSTMT1 = DBManip.getConn().createStatement();
			String tempSQL1 = "select * from deptCreditRequirement";
			System.out.println(tempSQL1);
			ResultSet tempRS1 = tempSTMT1.executeQuery(tempSQL1);
			while(tempRS1.next()){
				if(tempRS1.getString("dept").equals("软件学院")){
					seDPO1.minCourseCredits[0] = tempRS1.getInt("minCreditOfYr1");
					seDPO1.minCourseCredits[1] = tempRS1.getInt("minCreditOfYr2");
					seDPO1.minCourseCredits[2] = tempRS1.getInt("minCreditOfYr3");
					seDPO1.minCourseCredits[3] = tempRS1.getInt("minCreditOfYr4");
				}
				if(tempRS1.getString("dept").equals("地理学院")){
					geoDPO1.minCourseCredits[0] = tempRS1.getInt("minCreditOfYr1");
					geoDPO1.minCourseCredits[1] = tempRS1.getInt("minCreditOfYr2");
					geoDPO1.minCourseCredits[2] = tempRS1.getInt("minCreditOfYr3");
					geoDPO1.minCourseCredits[3] = tempRS1.getInt("minCreditOfYr4");
				}
				if(tempRS1.getString("dept").equals("环境学院")){
					evrDPO1.minCourseCredits[0] = tempRS1.getInt("minCreditOfYr1");
					evrDPO1.minCourseCredits[1] = tempRS1.getInt("minCreditOfYr2");
					evrDPO1.minCourseCredits[2] = tempRS1.getInt("minCreditOfYr3");
					evrDPO1.minCourseCredits[3] = tempRS1.getInt("minCreditOfYr4");
				}
				if(tempRS1.getString("dept").equals("商学院")){
					businessDPO1.minCourseCredits[0] = tempRS1.getInt("minCreditOfYr1");
					businessDPO1.minCourseCredits[1] = tempRS1.getInt("minCreditOfYr2");
					businessDPO1.minCourseCredits[2] = tempRS1.getInt("minCreditOfYr3");
					businessDPO1.minCourseCredits[3] = tempRS1.getInt("minCreditOfYr4");
				}
				if(tempRS1.getString("dept").equals("信管学院")){
					infoManagementDPO1.minCourseCredits[0] = tempRS1.getInt("minCreditOfYr1");
					infoManagementDPO1.minCourseCredits[1] = tempRS1.getInt("minCreditOfYr2");
					infoManagementDPO1.minCourseCredits[2] = tempRS1.getInt("minCreditOfYr3");
					infoManagementDPO1.minCourseCredits[3] = tempRS1.getInt("minCreditOfYr4");
				}
				
			}
			
			
			ret.add(seDPO1);
			ret.add(geoDPO1);
			
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

	/**
	 * Title: update
	 * Description:更新PO对象
	 * @author zhuyuanfu
	 * @param PO
	 * @return boolean
	 * @throws RemoteException
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean update(PO po) throws RemoteException { 
		if(po==null){
			System.out.println("参数是空的，操作失败");
			return false;
		}
		boolean updateSuccess = false;
		DBManip.connect();
		try{
			//删除该院系的所有课程
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "delete from deptplan where dept = '"+((DepartmentPlanPO)po).getDepartment()+"'";
			System.out.println(sql);
			stmt.execute(sql);
			//将该院系的所有课程重新写入
			Statement tempSTMT1 = DBManip.getConn().createStatement();
			for(int i = 0;i<((DepartmentPlanPO)po).getCourseList().size();i++){
				String tempSQL1 = "insert into deptplan (cno,dept) values ('"
							+((DepartmentPlanPO)po).getCourseList().get(i).getId()+"','"
							+((DepartmentPlanPO)po).getDepartment()+"')";
				System.out.println(tempSQL1);
				tempSTMT1.execute(tempSQL1);
			}
			//更新该院系的每学期学分要求
			Statement tempSTMT2 = DBManip.getConn().createStatement();
			String tempSQL2 = "update deptcreditrequirement set minCreditOfYr1 = '"
					+((DepartmentPlanPO)po).minCourseCredits[0]+"', minCreditOfYr2 = '"
					+((DepartmentPlanPO)po).minCourseCredits[1]+"', minCreditOfYr3 = '"
					+((DepartmentPlanPO)po).minCourseCredits[2]+"', minCreditOfYr4 = '"
					+((DepartmentPlanPO)po).minCourseCredits[3]+"' where dept = '"
					+((DepartmentPlanPO)po).getDepartment()+"'";
			System.out.println(tempSQL2);
			tempSTMT2.execute(tempSQL2);
			updateSuccess = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		DBManip.close();
		return updateSuccess;
	}

	/**
	 * Title: update
	 * Description:更新PO对象的某个属性值，返回操作成功与否
	 * @author zhuyuanfu
	 * @param PO, String, Object
	 * @return boolean
	 * @throws RemoteException
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
	 * Description:插入DepartmentPlanPO对象
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
		return false;
	}

	public boolean setSystemState(SYSTEM_STATE systemState,boolean isThisPersonLoginNow)
			throws RemoteException{
		return false;
	}
	
	public static void main(String[] args) throws RemoteException{
		DeptPlanDatabaseService dpds = new DeptPlanDatabaseService();
		ArrayList<PO> list1 = dpds.findAll();
		System.out.println(list1.size());
		System.out.println(((DepartmentPlanPO)list1.get(0)).getCourseList().size());
		System.out.println(((DepartmentPlanPO)list1.get(1)).getCourseList().size());
		
		System.out.println(((DepartmentPlanPO)list1.get(0)).getDepartment());
		
		System.out.print(((DepartmentPlanPO)list1.get(0)).minCourseCredits[0]);
		System.out.print(((DepartmentPlanPO)list1.get(0)).minCourseCredits[1]);
		System.out.print(((DepartmentPlanPO)list1.get(0)).minCourseCredits[2]);
		System.out.println(((DepartmentPlanPO)list1.get(0)).minCourseCredits[3]);
		System.out.print(((DepartmentPlanPO)list1.get(1)).minCourseCredits[0]);
		System.out.print(((DepartmentPlanPO)list1.get(1)).minCourseCredits[1]);
		System.out.print(((DepartmentPlanPO)list1.get(1)).minCourseCredits[2]);
		System.out.println(((DepartmentPlanPO)list1.get(1)).minCourseCredits[3]);
		
	}
	
	
}
