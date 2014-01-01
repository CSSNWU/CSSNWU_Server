/**
 * @(#)StudentDatabaseService.java     	2013-10-10 下午9:18:00
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.database;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;

import com.example.cssnwu.businesslogicservice.resultenum.CourseType;
import com.example.cssnwu.businesslogicservice.resultenum.Department;
import com.example.cssnwu.businesslogicservice.resultenum.SYSTEM_STATE;
import com.example.cssnwu.businesslogicservice.resultenum.StudentType;
import com.example.cssnwu.businesslogicservice.resultenum.UserType;
import com.example.cssnwu.databaseservice.DatabaseService;
import com.example.cssnwu.po.CoursePO;
import com.example.cssnwu.po.PO;
import com.example.cssnwu.po.StudentPO;
//import com.example.cssnwu.businesslogicservice.resultenum.StudentType;

/**
 *Class <code>StudentDatabaseService.java</code> 和Student对象有关的数据操作类
 *
 * @author never,zyf
 * @version 2013-10-10
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class StudentDatabaseService extends UnicastRemoteObject implements DatabaseService {

	/**空构造方法
	 * @throws RemoteException
	 */
	protected StudentDatabaseService() throws RemoteException {
		super();
	}

	/**
	 * Title: find
	 * Description:通过学号查询StudentPO，返回StudentPO
	 * @param id
	 * @return (StudentPO)PO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(int)
	 */
	@Override
	public PO find(int id) throws RemoteException {
		StudentPO ret = null;
		DBManip.connect();
		//  
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "select * from student where sno = '"+id+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				if(rs.getString("isLogin").equals("1")){
					ret = new  StudentPO(Integer.parseInt(rs.getString("sno")),
							rs.getString("name"),
							rs.getString("password"),
							true,
							UserType.Student
							);
				}
				if(rs.getString("isLogin").equals("0")){
					ret = new  StudentPO(Integer.parseInt(rs.getString("sno")),
							rs.getString("name"),
							rs.getString("password"),
							false,
							UserType.Student
							);
				}
				
				ret.setStudentType(StudentType.valueOf(rs.getString("studentType")));
				ret.setGrade(rs.getString("grade"));
				ret.setGpa(Double.parseDouble(rs.getString("gpa")));
				ret.setDepartment(Department.valueOf(rs.getString("dept")));
				ret.setTargetDepartment(Department.valueOf(rs.getString("wantdept")));
				ret.setYearIntoSchool(rs.getInt("yearOfAdmission"));
				
				//以下设置每个学生PO中的所有CoursePO
				ArrayList<CoursePO> coursePOList = new ArrayList<CoursePO>();
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "select * from course where cno in"
						+"(select cno from sc where sno = "+rs.getString("sno")
						+")";
				ResultSet tempRS1 = tempSTMT1.executeQuery(tempSQL1);
				while(tempRS1.next()){
					CoursePO tempCoursePO = new CoursePO(Integer.parseInt(tempRS1.getString("cno")));
					tempCoursePO.setCourseName(tempRS1.getString("name"));
//					if(tempRS1.getString("courseType").equals("must-study")){
//						tempCoursePO.setCourseType(CourseType.指选课);
//					}
//					if(tempRS1.getString("courseType").equals("select-study")){
//						tempCoursePO.setCourseType(CourseType.公选课);
//					}
					
					tempCoursePO.setCourseType(CourseType.valueOf(tempRS1.getString("courseType")));
					tempCoursePO.setCourseIntro(tempRS1.getString("courseIntroduction"));
					tempCoursePO.setCourseLocation(tempRS1.getString("place"));
					tempCoursePO.setCourseTime(tempRS1.getString("time"));
					tempCoursePO.setCredit(Integer.parseInt(tempRS1.getString("credit")));
					//下面将该课程教师列表放入课程PO
					ArrayList<String> teacherNameList = new ArrayList<String>();
					ArrayList<Integer> teacherIdList = new ArrayList<Integer>();
					Statement tempSTMT2 = DBManip.getConn().createStatement();
					String tempSQL2 = "select distinct name,tno from teacher where tno in"
							+"(select distinct tno from tc where cno = "+tempRS1.getString("cno")
							+")";
					ResultSet tempRS2 = tempSTMT2.executeQuery(tempSQL2);
					while(tempRS2.next()){
						teacherNameList.add(tempRS2.getString("name"));
						teacherIdList.add(Integer.parseInt(tempRS2.getString("tno")));
					}
					
					tempCoursePO.setTeacherNameList(teacherNameList);
					tempCoursePO.setTeacherIdList(teacherIdList);
					//下面将该生课程成绩放入课程PO
					Statement tempSTMT3 = DBManip.getConn().createStatement();
					String tempSQL3 = "select score from sc where sno = "+ id 
										+" and cno = "+tempRS1.getString("cno");
					ResultSet tempRS3 = tempSTMT3.executeQuery(tempSQL3);
					if(tempRS3.next()){
						tempCoursePO.setScore(tempRS3.getDouble("score"));
					}
					
					coursePOList.add(tempCoursePO);
					
				}
				
				ret.setCoursePOList(coursePOList);
				break;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return ret;
	}

	/**
	 * Title:  find 
	 * Description: 通过关键字查询不同类型的学生，可以是姓名中的某个字词，院系的字词，学生类型等
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
		try {
			Statement stmt;
			stmt = DBManip.getConn().createStatement();
			String sql = "select * from student where name like '%"+key+"%'" +
							" or dept like '"+key+"%'" +
							" or studentType like '"+key+"%'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				StudentPO studentPO = new  StudentPO(Integer.parseInt(rs.getString("sno")),
						rs.getString("name"),
						rs.getString("password"),
						false,
						UserType.Student
						);
				studentPO.setDepartment(Department.valueOf(rs.getString("dept")));
				studentPO.setTargetDepartment(Department.valueOf(rs.getString("wantdept")));
				studentPO.setGpa(rs.getDouble("gpa"));
				studentPO.setGrade(rs.getString("grade"));
				studentPO.setYearIntoSchool(rs.getInt("yearOfAdmission"));
				studentPO.setStudentType(StudentType.valueOf(rs.getString("studentType")));
				
				//以下设置每个学生PO中的所有CoursePO
				ArrayList<CoursePO> coursePOList = new ArrayList<CoursePO>();
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "select * from course where cno in"
						+"(select cno from sc where sno = "+rs.getString("sno")
						+")";
				ResultSet tempRS1 = tempSTMT1.executeQuery(tempSQL1);
				while(tempRS1.next()){
					CoursePO tempCoursePO = new CoursePO(Integer.parseInt(tempRS1.getString("cno")));
					tempCoursePO.setCourseName(tempRS1.getString("name"));
					if(tempRS1.getString("courseType").equals("must-study")){
						tempCoursePO.setCourseType(CourseType.指选课);
					}
					if(tempRS1.getString("courseType").equals("select-study")){
						tempCoursePO.setCourseType(CourseType.公选课);
					}
					
					
					
					tempCoursePO.setCourseIntro(tempRS1.getString("courseIntroduction"));
					tempCoursePO.setCourseLocation(tempRS1.getString("place"));
					tempCoursePO.setCourseTime(tempRS1.getString("time"));
					tempCoursePO.setCredit(Integer.parseInt(tempRS1.getString("credit")));
					
					ArrayList<String> teacherNameList = new ArrayList<String>();
					ArrayList<Integer> teacherIdList = new ArrayList<Integer>();
					Statement tempSTMT2 = DBManip.getConn().createStatement();
					String tempSQL2 = "select distinct name,tno from teacher where tno in"
							+"(select distinct tno from tc where cno = "+tempRS1.getString("cno")
							+")";
					ResultSet tempRS2 = tempSTMT2.executeQuery(tempSQL2);
					while(tempRS2.next()){
						teacherNameList.add(tempRS2.getString("name"));
						teacherIdList.add(Integer.parseInt(tempRS2.getString("tno")));
					}
					
					tempCoursePO.setTeacherNameList(teacherNameList);
					tempCoursePO.setTeacherIdList(teacherIdList);
					
					coursePOList.add(tempCoursePO);
					
				}
				
				studentPO.setCoursePOList(coursePOList);
				
				ret.add(studentPO);
				
			}
		} catch (SQLException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
		DBManip.close();
		return ret;
	}

	/**
	 * Title:  findAll
	 * Description: 查找数据库中所有学生的记录，生成一个ArrayList<StudentPO>返回
	 * @author zhuyuanfu
	 * @param null
	 * @return ArrayList<PO>
	 * @throws RemoteException
	 * @see com.example.cssnwu.databaseservice.DatabaseService#findAll()
	 */
	@Override
	public ArrayList<PO> findAll() throws RemoteException {
		ArrayList<PO> ret = new ArrayList<PO>();
		ret.clear();
		DBManip.connect();
		//区分关键字进行处理
		try{
			Statement stmt = DBManip.conn.createStatement();
			String sql ="select * from student";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				StudentPO spo = new  StudentPO(Integer.parseInt(rs.getString("sno")),
						rs.getString("name"),
						rs.getString("password"),
						false,
						UserType.Student
						);
				if(rs.getString("isLogin").equals("1")){
					spo.setIsLogin(true);
				}
				spo.setStudentType(StudentType.valueOf(rs.getString("studentType")));
				spo.setYearIntoSchool(rs.getInt("yearOfAdmission"));
				spo.setGrade(rs.getString("grade"));
				spo.setGpa(Double.parseDouble(rs.getString("gpa")));
				spo.setDepartment(Department.valueOf(rs.getString("dept")));
				spo.setTargetDepartment(Department.valueOf(rs.getString("wantdept")));
				
				//以下设置每个学生PO中的所有CoursePO
				ArrayList<CoursePO> coursePOList = new ArrayList<CoursePO>();
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "select * from course where cno in"
						+"(select cno from sc where sno = "+rs.getString("sno")
						+")";
				ResultSet tempRS1 = tempSTMT1.executeQuery(tempSQL1);
				while(tempRS1.next()){
					CoursePO tempCoursePO = new CoursePO(Integer.parseInt(tempRS1.getString("cno")));
					tempCoursePO.setCourseName(tempRS1.getString("name"));
//					if(tempRS1.getString("courseType").equals("must-study")){
//						tempCoursePO.setCourseType(CourseType.指选课);
//					}
//					if(tempRS1.getString("courseType").equals("select-study")){
//						tempCoursePO.setCourseType(CourseType.公选课);
//					}
					
					tempCoursePO.setCourseType(CourseType.valueOf(tempRS1.getString("courseType")));
					tempCoursePO.setCourseIntro(tempRS1.getString("courseIntroduction"));
					tempCoursePO.setCourseLocation(tempRS1.getString("place"));
					tempCoursePO.setCourseTime(tempRS1.getString("time"));
					tempCoursePO.setCredit(Integer.parseInt(tempRS1.getString("credit")));
					
					ArrayList<String> teacherNameList = new ArrayList<String>();
					ArrayList<Integer> teacherIdList = new ArrayList<Integer>();
					Statement tempSTMT2 = DBManip.getConn().createStatement();
					String tempSQL2 = "select distinct name,tno from teacher where tno in"
							+"(select distinct tno from tc where cno = "+tempRS1.getString("cno")
							+")";
					ResultSet tempRS2 = tempSTMT2.executeQuery(tempSQL2);
					while(tempRS2.next()){
						teacherNameList.add(tempRS2.getString("name"));
						teacherIdList.add(Integer.parseInt(tempRS2.getString("tno")));
					}
					
					tempCoursePO.setTeacherNameList(teacherNameList);
					tempCoursePO.setTeacherIdList(teacherIdList);
					
					coursePOList.add(tempCoursePO);
					
				}
				
				spo.setCoursePOList(coursePOList);
				
				ret.add(spo);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		DBManip.close();
		return ret;
		
	}

	/**
	 * Title:  findAll
	 * Description: 通过课程编号，找到所有选了这门课的学生PO
	 * @author zhuyuanfu
	 * @param int
	 * @return ArrayList<PO>
	 * @throws RemoteException
	 * @see com.example.cssnwu.databaseservice.DatabaseService#findAll(int)
	 */
	@Override
	public ArrayList<PO> findAll(int courseId) throws RemoteException {
		ArrayList<PO> ret = new ArrayList<PO>();
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql ="select * from student where student.sno in " +
					"(select sno from sc where sc.cno = '"+courseId+"');";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				StudentPO spo = new  StudentPO(Integer.parseInt(rs.getString("sno")),
						rs.getString("name"),
						rs.getString("password"),
						false,
						UserType.Student
						);
				if(rs.getString("isLogin").equals("1")){
					spo.setIsLogin(true);
				}
				spo.setStudentType(StudentType.valueOf(rs.getString("studentType")));
				spo.setYearIntoSchool(rs.getInt("yearOfAdmission"));
				spo.setGrade(rs.getString("grade"));
				spo.setGpa(Double.parseDouble(rs.getString("gpa")));
				spo.setDepartment(Department.valueOf(rs.getString("dept")));
				spo.setTargetDepartment(Department.valueOf(rs.getString("wantdept")));
				
				//以下设置每个学生PO中的所有CoursePO
				ArrayList<CoursePO> coursePOList = new ArrayList<CoursePO>();
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "select * from course where cno in"
						+"(select cno from sc where sno = "+rs.getString("sno")
						+")";
				ResultSet tempRS1 = tempSTMT1.executeQuery(tempSQL1);
				while(tempRS1.next()){
					CoursePO tempCoursePO = new CoursePO(Integer.parseInt(tempRS1.getString("cno")));
					tempCoursePO.setCourseName(tempRS1.getString("name"));
//					if(tempRS1.getString("courseType").equals("must-study")){
//						tempCoursePO.setCourseType(CourseType.指选课);
//					}
//					if(tempRS1.getString("courseType").equals("select-study")){
//						tempCoursePO.setCourseType(CourseType.公选课);
//					}
					
					tempCoursePO.setCourseType(CourseType.valueOf(tempRS1.getString("courseType")));
					tempCoursePO.setCourseIntro(tempRS1.getString("courseIntroduction"));
					tempCoursePO.setCourseLocation(tempRS1.getString("place"));
					tempCoursePO.setCourseTime(tempRS1.getString("time"));
					tempCoursePO.setCredit(Integer.parseInt(tempRS1.getString("credit")));
					
					ArrayList<String> teacherNameList = new ArrayList<String>();
					ArrayList<Integer> teacherIdList = new ArrayList<Integer>();
					Statement tempSTMT2 = DBManip.getConn().createStatement();
					String tempSQL2 = "select distinct name,tno from teacher where tno in"
							+"(select distinct tno from tc where cno = "+tempRS1.getString("cno")
							+")";
					ResultSet tempRS2 = tempSTMT2.executeQuery(tempSQL2);
					while(tempRS2.next()){
						teacherNameList.add(tempRS2.getString("name"));
						teacherIdList.add(Integer.parseInt(tempRS2.getString("tno")));
					}
					
					tempCoursePO.setTeacherNameList(teacherNameList);
					tempCoursePO.setTeacherIdList(teacherIdList);
					
					coursePOList.add(tempCoursePO);
					
				}
				spo.setCoursePOList(coursePOList);
				ret.add(spo);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return ret;
	}

	/**
	 * Title:  update
	 * Description: 更新一个(StudentPO)PO，返回true，如果发生错误或异常则返回false
	 * @author zhuyuanfu
	 * @param PO
	 * @return boolean
	 * @throws RemoteException
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean update(PO po) throws RemoteException {
		//检查传入的参数是否为空
		if(po == null){
			System.out.println("参数是空，不能进行update操作");
			return false;
		}
		
		boolean updateSuccessful = false;
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "update student set "+
						" name = "+((StudentPO)po).getUserName()+","+
						" password = '"+((StudentPO)po).getPassword()+"' "+
						" userType = '"+((StudentPO)po).getUserType()+"' "+
						" dept = '"+((StudentPO)po).getDepartment()+"' "+
						" grade = '"+((StudentPO)po).getGrade()+"',"+
						" yearOfAdmission = '"+((StudentPO)po).getYearIntoSchool()+"',"+
						" gpa = "+((StudentPO)po).getGpa()+","+
						" wantdept = '"+((StudentPO)po).getTargetDepartment()+"',"+
						" studentType = '"+((StudentPO)po).getStudentType()+"'"+
						" where sno = '"+po.getId()+"'"+
						";";
			System.out.println(sql);
			stmt.execute(sql);
			
			
			//更新学生选课列表。先判断参数的课程列表是否空
			if(((StudentPO)po).getCoursePOList()!=null){
				ArrayList<CoursePO> coursePOList = ((StudentPO)po).getCoursePOList();
				ArrayList<Integer> courseIdList = new ArrayList<Integer>();
				for(int i = 0;i<coursePOList.size();i++){
					courseIdList.add(coursePOList.get(i).getId());
				}
				
				System.out.println(courseIdList);
				
				//删去该生所有的课程列表
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "delete  from sc where sno = "+ po.getId();
				tempSTMT1.execute(tempSQL1);
				
				//将该生的所有课程写入数据库。
				//实际上可以使用一个insert语句将许多元组插入一张表【泥垢
				//这里采用了偷懒的方法，多次使用insert语句一行一行插入【吐槽去史
				for(int i = 0 ; i < courseIdList.size();i++){
					Statement tempSTMT2 = DBManip.getConn().createStatement();
					String tempSQL2 = "insert into sc (sno,cno,score) " +
							" values ( "
							+po.getId()+" , "
							+courseIdList.get(i)+" , "
							+coursePOList.get(i).getScore()
							+" )";
					
					System.out.println(tempSQL2);
					tempSTMT2.execute(tempSQL2);
				}
			}
			System.out.println("student update ....");
			updateSuccessful = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return updateSuccessful;
	}

	/**
	 * Title:  update 
	 * Description: 更新一个学生信息，可以指定学生表中的属性和值
	 * @author zhuyuanfu
	 * @param PO,String,Object
	 * @return boolean
	 * @throws RemoteException
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean update(PO po, String attr, Object value)
			throws RemoteException {
		DBManip.connect();
		try{
			//更新学生本人信息
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "update student set "+attr+" = '"+String.valueOf(value)
					+" where sno = '"+po.getId()+"'";
			stmt.execute(sql);
			
			//先进行课程列表是否为空的判断，再更新学生选课信息。
			if(((StudentPO)po).getCoursePOList()!=null){
				ArrayList<CoursePO> coursePOList = ((StudentPO)po).getCoursePOList();
				ArrayList<Integer> courseIdList = new ArrayList<Integer>();
				for(int i = 0;i<coursePOList.size();i++){
					courseIdList.add(coursePOList.get(i).getId());
				}
				
				//删去学生的选课信息
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "delete from sc where sno = "+ po.getId();
				System.out.println(tempSQL1);
				tempSTMT1.execute(tempSQL1);
				//重新写入选课信息
				for(int i = 0 ; i < courseIdList.size();i++){
					Statement tempSTMT2 = DBManip.getConn().createStatement();
					String tempSQL2 = "insert into sc (sno,cno,score) " +
							" values ( "
							+po.getId()+" , "
							+courseIdList.get(i)+" , "
							+coursePOList.get(i).getScore()
							+" )";
					tempSTMT2.execute(tempSQL2);
				}
			}
			System.out.println("student update ....");
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		DBManip.close();
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

	/**
	 * Title:insert
	 * Description:将一个(StudentPO)po对象插入数据库，po的所有属性都写入数据库
	 * @param PO
	 * @return boolean
	 * @throws RemoteException
	 * @author zhuyuanfu
	 * @see com.example.cssnwu.databaseservice#insert(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean insert(PO po) throws RemoteException {
		boolean insertSuccessful = false;
		DBManip.connect();
		try{
			//将学生基本信息插入学生表
			Statement stmt = DBManip.conn.createStatement();
			String sql = "insert into student (sno,yearOfAdmission,name,password,userType,dept,grade,gpa,wantdept,studentType )" +
					" values ('"+((StudentPO)po).getId()+
					"','"+((StudentPO)po).getYearIntoSchool()+
					"','"+((StudentPO)po).getUserName()+
					"','"+((StudentPO)po).getPassword()+
					"','"+((StudentPO)po).getUserType()+
					"','"+((StudentPO)po).getDepartment()+
					"','"+((StudentPO)po).getGrade()+
					"','"+((StudentPO)po).getGpa()+
					"','"+((StudentPO)po).getTargetDepartment()+
					"','"+((StudentPO)po).getStudentType()+
					"');";
			System.out.println(sql);
			stmt.execute(sql);
			
			//如果这人的选课记录不为空，将选课记录插入SC表
			if(((StudentPO)po).getCoursePOList()!=null){
				for(int i = 0;i<((StudentPO)po).getCoursePOList().size();i++){
					Statement tempSTMT1 = DBManip.getConn().createStatement();
					String tempSQL1  = "insert intp sc ( sno , cno , score ) "+
										" values ( '"+((StudentPO)po).getId()+"' , '"+
										((StudentPO)po).getCoursePOList().get(i).getId()+"' , '"+
										((StudentPO)po).getCoursePOList().get(i).getScore()+
										"' )";
					System.out.println(tempSQL1);
					tempSTMT1.execute(tempSQL1);
				}
			}
			//插入完毕之后，设置返回值为真
			insertSuccessful = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return insertSuccessful;
	}

	/**
	 * Title: delete
	 * Description:从数据库删除PO对象
	 * @author zhuyuanfu
	 * @param PO
	 * @return boolean
	 * @see com.example.cssnwu.databaseservice.DatabaseService#delete(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean delete(PO po) throws RemoteException {
		boolean deleteSuccessful = false;
		DBManip.connect();
		try{
			Statement stmt = DBManip.conn.createStatement();
			String sql = "delete  from student where sno = '"+po.getId()+"'";
			
			System.out.println(sql);//检查mysql语句
			
			stmt.execute(sql);
			
			Statement tempSTMT1 = DBManip.getConn().createStatement();
			String tempSQL1 = "delete from sc where sno = '"+po.getId()+"'";
			System.out.println(tempSTMT1);//检查mysql语句
			tempSTMT1.execute(tempSQL1);
			deleteSuccessful = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return deleteSuccessful;
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

	/**
	 * Title: finish
	 * Description:结束对数据的操作
	 * @since JDK1.7
	 * @version 2013.12.28
	 * @author zhuyuanfu
	 * @see com.example.cssnwu.databaseservice.DatabaseService#finish()
	 */
	@Override
	public void finish() throws RemoteException {
	}
	
	@Override
	public boolean checkSystemState(SYSTEM_STATE system_state) throws RemoteException {
		return false;
	}
	
	public boolean setSystemState(SYSTEM_STATE systemState,boolean isThisPersonLoginNow)
			throws RemoteException{
		return false;
	}
	
	public static void main(String [] args) throws RemoteException{
		StudentDatabaseService sds = new StudentDatabaseService();
		
		PO aPO = sds.find(111160126);
		System.out.println(((StudentPO)aPO).getCoursePOList().get(0).getTeacherNameList());
		System.out.println(((StudentPO)aPO).getCoursePOList().get(1).getTeacherIdList());
		
		ArrayList<PO> s = sds.find("staydown");
		System.out.println("name " + s.isEmpty());
		
		
		PO spo = sds.find(1);
		
		
		System.out.println(((StudentPO)spo).getCoursePOList().get(0).getScore());
		System.out.println(((StudentPO)spo).getCoursePOList().get(1).getScore());
		
		
		ArrayList<PO> t = sds.find("地理");
		System.out.println(t.size());
	}

	

}
