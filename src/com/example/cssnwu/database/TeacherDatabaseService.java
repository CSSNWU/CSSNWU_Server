/**
 * @(#)TeacherDatabaseService.java     	2013-10-10 ����8:44:42
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
import java.util.Iterator;

import com.example.cssnwu.businesslogicservice.resultenum.CourseType;
import com.example.cssnwu.businesslogicservice.resultenum.Department;
import com.example.cssnwu.businesslogicservice.resultenum.SYSTEM_STATE;
import com.example.cssnwu.businesslogicservice.resultenum.UserType;
import com.example.cssnwu.databaseservice.DatabaseService;
import com.example.cssnwu.po.CoursePO;
import com.example.cssnwu.po.PO;
import com.example.cssnwu.po.StudentPO;

import com.example.cssnwu.po.TeacherPO;

/**
 *Class <code>TeacherDatabaseService.java</code> ��Teacher�����йص����ݲ�����
 *
 * @author never
 * @version 2013-10-10
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class TeacherDatabaseService extends UnicastRemoteObject implements DatabaseService{

	/**���췽��
	 * @throws RemoteException
	 */
	protected TeacherDatabaseService() throws RemoteException {
		super();
	}

	/**
	 * Title: find
	 * Description:ͨ�����Ų�ѯTeacherPO
	 * @author zhuyuanfu
	 * @param int
	 * @return (TeacherPO)PO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(int)
	 */
	@Override
	public PO find(int id) throws RemoteException {
		TeacherPO ret = null;
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "select * from teacher where tno = "+id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				ret = new TeacherPO(rs.getString("name"),
						rs.getString("password"),
						false,
						UserType.Teacher);
				ret.setId(rs.getInt("tno"));
				ret.setDepartment(Department.valueOf(rs.getString("dept")));
				if(rs.getInt("isLogin")==1){
					ret.setIsLogin(true);
				}
				if(rs.getInt("isLogin")==0){
					ret.setIsLogin(false);
				}
				
				
				
				//�������õ���TeacherPO��Ķ�CoursePO�б�
				ArrayList<CoursePO> coursePOlist1 = new ArrayList<CoursePO>();
				
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "select * from course where cno in "+
						"( select cno from tc where tno = "+rs.getString("tno")+" )" ;
				ResultSet tempRS1 = tempSTMT1.executeQuery(tempSQL1);
				while(tempRS1.next()){
					//����ȡ������CoursePO������CoursePO����
					CoursePO tempCoursePO = new CoursePO(tempRS1.getInt("cno"));
					tempCoursePO.setCourseName(tempRS1.getString("name"));
					tempCoursePO.setCourseTime(tempRS1.getString("time"));
					tempCoursePO.setCourseLocation(tempRS1.getString("place"));
					tempCoursePO.setCredit(tempRS1.getInt("credit"));
					tempCoursePO.setCourseIntro(tempRS1.getString("courseIntroduction"));
					tempCoursePO.setEstablishTime(tempRS1.getString("startTime"));
//					if(tempRS1.getString("courseType").equals("must-study")){
//						tempCoursePO.setCourseType(CourseType.ָѡ��);
//					}
//					if(tempRS1.getString("courseType").equals("select-study")){
//						tempCoursePO.setCourseType(CourseType.��ѡ��);
//					}
					
					tempCoursePO.setCourseType(CourseType.valueOf(tempRS1.getString("courseType")));
					
					//���潫��ʦ�����б�͹����б����tempCoursePO
					ArrayList<Integer> teacherIdList = new ArrayList<Integer>();
					ArrayList<String> teacherNameList = new ArrayList<String>();
					
					Statement tempSTMT2 = DBManip.getConn().createStatement();
					String tempSQL2 = "select tno,name from teacher where tno in "+
							"( select tno from tc where cno = "+tempRS1.getString("cno")+" )" ;
					ResultSet tempRS2 = tempSTMT2.executeQuery(tempSQL2);
					while(tempRS2.next()){
						teacherNameList.add(tempRS2.getString("name"));
						teacherIdList.add(tempRS2.getInt("tno"));
					}
					
					tempCoursePO.setTeacherIdList(teacherIdList);
					tempCoursePO.setTeacherNameList(teacherNameList);
					
					coursePOlist1.add(tempCoursePO);//��������ϵ�CoursePO��ӵ�ArrayList<CoursePO>
				}
				ret.setCoursePOList(coursePOlist1);
				//���Է����Ա��
				if(ret.getCoursePOList()==null){
					ret.setCoursePOList(new ArrayList<CoursePO>());
				}
				break;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return ret;
	}

	/** 
	 * Title: find
	 * Description:ͨ��Ժϵ��������ѯTeacherPO
	 * @author zhuyuanfu
	 * @param String
	 * @return ArrayList<(TeacherPO)PO>
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(java.lang.String)
	 */
	@Override
	public ArrayList<PO> find(String key) throws RemoteException {
		boolean Debug = false;
		
		ArrayList<PO> teacherPOList = new ArrayList<PO>();
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "select * from teacher where dept like '%"+key
						+"%' or name like '&"+key
						+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				//����ʦ������Ϣ����tempTeacherPO
				TeacherPO tempTeacherPO = new TeacherPO(rs.getInt("tno"));
				tempTeacherPO.setUserName(rs.getString("name"));
				tempTeacherPO.setPassword(rs.getString("password"));
				tempTeacherPO.setUserType(UserType.Teacher);
				tempTeacherPO.setDepartment(Department.valueOf(key));
				if(rs.getInt("isLogin")==1){
					tempTeacherPO.setIsLogin(true);
				}
				if(rs.getInt("isLogin")==0){
					tempTeacherPO.setIsLogin(false);
				}
				
				//������ʦ�̵����пγ��б����tempTeacherPO
				ArrayList<CoursePO> coursePOlist1 = new ArrayList<CoursePO>();
				coursePOlist1.clear();
				
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "select * from course where cno in "+
						"( select cno from tc where tno = "+rs.getString("tno")+" )" ;
				ResultSet tempRS1 = tempSTMT1.executeQuery(tempSQL1);
				while(tempRS1.next()){
					//����ȡ������CoursePO������CoursePO����
					CoursePO tempCoursePO = new CoursePO(tempRS1.getInt("cno"));
					tempCoursePO.setCourseName(tempRS1.getString("name"));
					tempCoursePO.setCourseTime(tempRS1.getString("time"));
					tempCoursePO.setCourseLocation(tempRS1.getString("place"));
					tempCoursePO.setCredit(tempRS1.getInt("credit"));
					tempCoursePO.setCourseIntro(tempRS1.getString("courseIntroduction"));
					tempCoursePO.setEstablishTime(tempRS1.getString("startTime"));
//					if(tempRS1.getString("courseType").equals("must-study")){
//						tempCoursePO.setCourseType(CourseType.ָѡ��);
//					}
//					if(tempRS1.getString("courseType").equals("select-study")){
//						tempCoursePO.setCourseType(CourseType.��ѡ��);
//					}
					
					tempCoursePO.setCourseType(CourseType.valueOf(tempRS1.getString("courseType")));
					
					//���潫��ʦ�����б�͹����б����tempCoursePO
					ArrayList<Integer> teacherIdList = new ArrayList<Integer>();
					ArrayList<String> teacherNameList = new ArrayList<String>();
					
					Statement tempSTMT2 = DBManip.getConn().createStatement();
					String tempSQL2 = "select tno,name from teacher where tno in "+
							"( select tno from tc where cno = "+tempRS1.getString("cno")+" )" ;
					ResultSet tempRS2 = tempSTMT2.executeQuery(tempSQL2);
					while(tempRS2.next()){
						teacherNameList.add(tempRS2.getString("name"));
						teacherIdList.add(tempRS2.getInt("tno"));
					}
					
					
					tempCoursePO.setTeacherIdList(teacherIdList);
					tempCoursePO.setTeacherNameList(teacherNameList);
					
					
					
					coursePOlist1.add(tempCoursePO);//��������ϵ�CoursePO����ArrayList<CoursePO>
					
					//if(Debug) System.out.println(coursePOlist1.isEmpty());
					
					
					
					
				}
				
				tempTeacherPO.setCoursePOList(coursePOlist1);
				
				if(Debug) System.out.println(coursePOlist1.isEmpty());
				
				if(tempTeacherPO.getCoursePOList()==null){
					tempTeacherPO.setCoursePOList(new ArrayList<CoursePO>());
				}//�����Ա��
				
				teacherPOList.add(tempTeacherPO);
				
//				if(Debug) {
//					for(int i = 0;i<teacherPOList.size();i++){
//						System.out.println(i+" "+((TeacherPO)teacherPOList.get(i)).getCoursePOList().isEmpty());
//					}
//				}
				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return teacherPOList;
	}

	/**
	 * Title: findAll
	 * Description:��ѯ����TeacherPO
	 * @author zhuyuanfu
	 * @param null
	 * @return ArrayList<(TeacherPO)PO>
	 * @see com.example.cssnwu.databaseservice.DatabaseService#findAll()
	 */
	@Override
	public ArrayList<PO> findAll() throws RemoteException {
		ArrayList<PO> teacherPOList = new ArrayList<PO>();
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "select * from teacher";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				//����ʦ������Ϣ����tempTeacherPO
				TeacherPO tempTeacherPO = new TeacherPO(rs.getInt("tno"));
				tempTeacherPO.setUserName(rs.getString("name"));
				tempTeacherPO.setPassword(rs.getString("password"));
				tempTeacherPO.setUserType(UserType.Teacher);
				tempTeacherPO.setDepartment(Department.valueOf(rs.getString("dept")));
				if(rs.getInt("isLogin")==1){
					tempTeacherPO.setIsLogin(true);
				}
				if(rs.getInt("isLogin")==0){
					tempTeacherPO.setIsLogin(false);
				}
				
				
				//������ʦ�̵����пγ��б����tempTeacherPO
				ArrayList<CoursePO> coursePOlist1 = new ArrayList<CoursePO>();
				coursePOlist1.clear();
				
				//����ȡ������CoursePO������coursePOlist1����
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "select * from course where cno in "+
						"( select cno from tc where tno = "+rs.getString("tno")+" )" ;
				ResultSet tempRS1 = tempSTMT1.executeQuery(tempSQL1);
				while(tempRS1.next()){
					
					CoursePO tempCoursePO = new CoursePO(tempRS1.getInt("cno"));
					tempCoursePO.setCourseName(tempRS1.getString("name"));
					tempCoursePO.setCourseTime(tempRS1.getString("time"));
					tempCoursePO.setCourseLocation(tempRS1.getString("place"));
					tempCoursePO.setCredit(tempRS1.getInt("credit"));
					tempCoursePO.setCourseIntro(tempRS1.getString("courseIntroduction"));
					tempCoursePO.setEstablishTime(tempRS1.getString("startTime"));
					if(tempRS1.getString("courseType").equals("must-study")){
						tempCoursePO.setCourseType(CourseType.ָѡ��);
					}
					if(tempRS1.getString("courseType").equals("select-study")){
						tempCoursePO.setCourseType(CourseType.��ѡ��);
					}
					tempCoursePO.setCourseType(CourseType.valueOf(tempRS1.getString("courseType")));
					
					//���潫��ʦ�����б�͹����б����tempCoursePO
					ArrayList<Integer> teacherIdList = new ArrayList<Integer>();
					ArrayList<String> teacherNameList = new ArrayList<String>();
					
					Statement tempSTMT2 = DBManip.getConn().createStatement();
					String tempSQL2 = "select tno,name from teacher where tno in "+
							"( select tno from tc where cno = "+tempRS1.getString("cno")+" )" ;
					ResultSet tempRS2 = tempSTMT2.executeQuery(tempSQL2);
					while(tempRS2.next()){
						teacherNameList.add(tempRS2.getString("name"));
						teacherIdList.add(tempRS2.getInt("tno"));
					}
					
					
					tempCoursePO.setTeacherIdList(teacherIdList);
					tempCoursePO.setTeacherNameList(teacherNameList);
					
					
					
					coursePOlist1.add(tempCoursePO);//��������ϵ�CoursePO����ArrayList<CoursePO>
					
					//if(Debug) System.out.println(coursePOlist1.isEmpty());
					
					
					
					
				}
				
				
				
				System.out.println("is coursePOlist1 empty ? "+coursePOlist1.isEmpty());
				tempTeacherPO.setCoursePOList(coursePOlist1);
				
				System.out.print(tempTeacherPO.getUserName()+" ");
				System.out.println(tempTeacherPO.getDepartment());

				
				
				teacherPOList.add(tempTeacherPO);
						
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return teacherPOList;
				
	}

	/**
	 * Title: findAll
	 * Description:���ҽ���ĳ�γ̵�������ʦ
	 * @author zhuyuanfu
	 * @param int
	 * @return ArrayList<(TeacherPO)PO>
	 * @see com.example.cssnwu.databaseservice.DatabaseService#findAll(int)
	 */
	@Override
	public ArrayList<PO> findAll(int courseId) throws RemoteException {
		ArrayList<PO> ret = new ArrayList<PO>();

		DBManip.connect();
		try{
			Statement stmt = DBManip.conn.createStatement();
			String sql ="select * from teacher where tno in " +
					"(select tno from tc where cno = '"+courseId+"');";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				TeacherPO tempTeacherPO = new TeacherPO();
				tempTeacherPO = new TeacherPO(rs.getString("name"),
						rs.getString("password"),
						false,
						UserType.Teacher);
				if(rs.getInt("isLogin")==1){
					tempTeacherPO.setIsLogin(true);
				}
				tempTeacherPO.setDepartment(Department.valueOf(rs.getString("dept")));
				tempTeacherPO.setId(rs.getInt("tno"));
				//�ҵ�����ʦ�����пγ̣��������Ŀγ��б�
				ArrayList<CoursePO> coursePOList = new ArrayList<CoursePO>();
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "select * from course where cno in"
						+"(select cno from tc where tno = '"+tempTeacherPO.getId()+"')";
				ResultSet tempRS1 = tempSTMT1.executeQuery(tempSQL1);
				while(tempRS1.next()){
					CoursePO tempCoursePO = new CoursePO(tempRS1.getInt("cno"));
					tempCoursePO.setCourseName(tempRS1.getString("name"));
					tempCoursePO.setCourseLocation(tempRS1.getString("place"));
					tempCoursePO.setCourseTime(tempRS1.getString("time"));
					tempCoursePO.setCourseIntro(tempRS1.getString("courseIntroduction"));
					tempCoursePO.setCourseType(CourseType.valueOf(tempRS1.getString("courseType")));
					tempCoursePO.setCredit(tempRS1.getInt("creadit"));
					tempCoursePO.setEstablishTime(tempRS1.getString("startTime"));
					
					//���ÿγ̵������ο���ʦ�������͹��żӵ������б�͹����б���
					ArrayList<Integer> teacherIdList = new ArrayList<Integer>();
					ArrayList<String> teacherNameList = new ArrayList<String>();
					Statement tempSTMT2 = DBManip.getConn().createStatement();
					String tempSQL2 = "select name , tno from teacher where tno in "
							+"(select tno from tc where cno = '"+tempCoursePO.getId()+"')";
					System.out.println(tempSQL2);
					ResultSet tempRS2 = tempSTMT2.executeQuery(tempSQL2);
					while(tempRS2.next()){
						teacherIdList.add(tempRS2.getInt("tno"));
						teacherNameList.add(tempRS2.getString("name"));
						
					}
					tempCoursePO.setTeacherIdList(teacherIdList);
					tempCoursePO.setTeacherNameList(teacherNameList);
					coursePOList.add(tempCoursePO);
				}
				
				tempTeacherPO.setCoursePOList(coursePOList);
				ret.add(tempTeacherPO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return ret;
	}

	/**
	 * Title: update
	 * Description:����PO����
	 * @author zhuyuanfu
	 * @param PO
	 * @return boolean
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean update(PO po) throws RemoteException {
		boolean updateSuccessful = false;
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "update teacher set "+
					" tno = '"+((TeacherPO)po).getId()+
					"', name = '"+((TeacherPO)po).getUserName()+
					"', password = '"+((TeacherPO)po).getPassword()+
					"', userType = '"+((TeacherPO)po).getUserType()+
					"', dept = '"+((TeacherPO)po).getDepartment()+
					"' where tno = '"+String.valueOf(po.getId())+"'"+
					";";
			
			System.out.println(sql);
			stmt.execute(sql);
			//���¸��¸���ʦ���ڿμ�¼����������Ҫ�½���Щ�Σ���Щ�β���Ҫ�����ˣ�etc.��
			
			//���t��eacher��c��ourse�����и���ʦ���еļ�¼
			Statement tempSTMT0 = DBManip.getConn().createStatement();
			String tempSQL0 = "delete from tc where tno = "+po.getId();
			tempSTMT0.execute(tempSQL0);
			
			//������ʦ���е������ڿμ�¼����д��tc��
			ArrayList<CoursePO> coursePOList = ((TeacherPO)po).getCoursePOList();
			ArrayList<Integer> courseIdList = new ArrayList<Integer>();
			for(int i = 0;i<coursePOList.size();i++){
				courseIdList.add(coursePOList.get(i).getId());
			}
			
			for(int i = 0;i<courseIdList.size();i++){
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "insert into tc (tno,cno) " +
						" values ( '"
						+po.getId()+"' , '"
						+courseIdList.get(i)
						+"' )";
				System.out.println(tempSQL1);
				tempSTMT1.execute(tempSQL1);
			}
			
			System.out.println("teacher and his courses update ....");
			
			//���ϸ��¸���ʦ���ڿ�
			updateSuccessful = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return updateSuccessful;
	}

	/**
	 * Title: update
	 * Description:����PO�����ĳ������ֵ	
	 * @author zhuyuanfu
	 * @param PO,String,Object
	 * @return boolean
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean update(PO po, String attr, Object value)
			throws RemoteException {
		boolean updateSuccessful = false;
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "update teacher set "+		
						attr+" = "+value+
						" where tno = '"+String.valueOf(po.getId())+"'"+
						";";
			
			stmt.execute(sql);
			//���¸��¸���ʦ���ڿμ�¼����������Ҫ�½���Щ�Σ���Щ�β���Ҫ�����ˣ�etc.��
			
			//���t��eacher��c��ourse�����и���ʦ���еļ�¼
			Statement tempSTMT0 = DBManip.getConn().createStatement();
			String tempSQL0 = "delete from tc where tno = "+po.getId();
			System.out.println(tempSQL0);
			tempSTMT0.execute(tempSQL0);
			
			//������ʦ���е������ڿμ�¼����д��tc��
			ArrayList<CoursePO> coursePOList = ((TeacherPO)po).getCoursePOList();
			ArrayList<Integer> courseIdList = new ArrayList<Integer>();
			for(int i = 0;i<coursePOList.size();i++){
				courseIdList.add(coursePOList.get(i).getId());
			}
			
			for(int i = 0;i<courseIdList.size();i++){
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "insert into tc (tno,cno) " +
						" values ( '"
						+po.getId()+"' , '"
						+courseIdList.get(i)+"' "
						+" )";
				System.out.println(tempSQL1);
				tempSTMT1.execute(tempSQL1);
			}
			
			System.out.println("teacher and his/her courses update ....");
			
			//���ϸ��¸���ʦ���ڿ�
			updateSuccessful = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return updateSuccessful;
	}

	/**
	 * Title: update
	 * Description:��ѧ���ǼǷ���
	 * @author zhuyuanfu
	 * @param int, HashMap<Integer,Double>
	 * @return boolean
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(int, java.util.HashMap)
	 */
	@Override
	public boolean update(int courseId, HashMap<Integer, Double> map)
			throws RemoteException {
		if(new CourseDatabaseService().find(courseId)==null){
			System.out.println("�ÿγ̲����ڡ������ĸ���������");
			return false;
		}
		boolean updateSuccess = false;
		DBManip.connect();
		try{
			int sno = 0;
			double score = 0;
			
			Iterator<Integer> it = map.keySet().iterator();
			while(it.hasNext()){
				sno = it.next().intValue();
				score = map.get(sno);
				Statement stmt = DBManip.getConn().createStatement();
				String sql = "update sc set score = '"+score
						+"' where sno = '"+sno+"' and cno = '"+courseId+"'";
				
				System.out.println(sql);
				stmt.execute(sql);
			}
			
			updateSuccess = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		DBManip.close();
		return updateSuccess;
	}

	/**
	 * Title: insert
	 * Description:����TeacherPO����
	 * @author zhuyuanfu
	 * @param PO
	 * @return boolean
	 * @see com.example.cssnwu.databaseservice.DatabaseService#insert(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean insert(PO po) throws RemoteException {
		boolean insertSuccessful = false;
		DBManip.connect();
		try{
			//�����ʦ������Ϣ
			Statement stmt = DBManip.conn.createStatement();
			String sql = "insert into teacher (tno,name,password,userType,dept,isLoin)" +
					" values ('"+((TeacherPO)po).getId()+
					"','"+((TeacherPO)po).getUserName()+
					"','"+((TeacherPO)po).getPassword()+
					"','"+((TeacherPO)po).getUserType()+
					"','"+((TeacherPO)po).getDepartment()+
					"','"+"0"+
					"');";
			System.out.println(sql);
			stmt.execute(sql);
			//���¸��¸���ʦ���ڿμ�¼����������Ҫ�½���Щ�Σ���Щ�β���Ҫ�����ˣ�etc.��
			
			//���t��eacher��c��ourse�����и���ʦ���еļ�¼
			Statement tempSTMT0 = DBManip.getConn().createStatement();
			String tempSQL0 = "delete from tc where tno = "+po.getId();
			System.out.println(tempSQL0);
			tempSTMT0.execute(tempSQL0);
			
			//������ʦ���е������ڿμ�¼����д��tc��
			ArrayList<CoursePO> coursePOList = ((TeacherPO)po).getCoursePOList();
			ArrayList<Integer> courseIdList = new ArrayList<Integer>();
			for(int i = 0;i<coursePOList.size();i++){
				courseIdList.add(coursePOList.get(i).getId());
			}
			
			for(int i = 0;i<courseIdList.size();i++){
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "insert into tc (tno,cno) " +
						" values ( '"
						+po.getId()+"' , '"
						+courseIdList.get(i)+"' "
						+" )";
				System.out.println(tempSQL1);
				tempSTMT1.execute(tempSQL1);
			}
			
			System.out.println("teacher and his/her courses update ....");
			
			//���ϸ��¸���ʦ���ڿ�
			
			insertSuccessful = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return insertSuccessful;
	}

	/**
	 * Title: delete
	 * Description:ɾ��PO����
	 * @author zhuyuanfu
	 * @param ��TeacherPO��PO
	 * @return boolean
	 * @see com.example.cssnwu.databaseservice.DatabaseService#delete(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean delete(PO po) throws RemoteException {
		boolean deleteSuccessful = false;
		DBManip.connect();
		try{
			Statement stmt = DBManip.conn.createStatement();
			String sql = "delete  from teacher where tno = '"+po.getId()+"'";
			System.out.println(sql);
			stmt.execute(sql);
			
			//���¸��¸���ʦ���ڿμ�¼����������Ҫ�½���Щ�Σ���Щ�β���Ҫ�����ˣ�,,,
			//						��ɾȥ����ʦ...�������п�ҲӦ��һ����ɾȥ�ɡ���etc.��
			
			//���t��eacher��c��ourse�����и���ʦ���еļ�¼
			Statement tempSTMT0 = DBManip.getConn().createStatement();
			String tempSQL0 = "delete from tc where tno = "+po.getId();
			System.out.println(tempSQL0);
			tempSTMT0.execute(tempSQL0);
			//������ո���ʦ���ڿ�
			
			deleteSuccessful = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return deleteSuccessful;
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
	public boolean checkSystemState(SYSTEM_STATE system_state) throws RemoteException{
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean setSystemState(SYSTEM_STATE systemState,boolean isThisPersonLoginNow)
			throws RemoteException{
		return false;
	}
	
	public static void main(String [] args) throws RemoteException{
		TeacherDatabaseService tds = new TeacherDatabaseService();
		
		System.out.println("------------------------");
		HashMap<Integer,Double> map = new HashMap<Integer, Double>();
		
		map.put(111160126, (double)88);
		map.put(1, 35.6);
		
		tds.update(20100, map);			//��ʾ�����20100�Ŀγ̵ĳɼ��������ݿ�
		
	}


}
