/**
 * @(#)CourseDatabaseService.java     	2013-10-10 下午8:37:25
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
import java.util.Iterator;

import com.example.cssnwu.businesslogicservice.resultenum.CourseType;
import com.example.cssnwu.businesslogicservice.resultenum.SYSTEM_STATE;
import com.example.cssnwu.databaseservice.DatabaseService;
import com.example.cssnwu.po.PO;
import com.example.cssnwu.po.CoursePO;


/**
 *Class <code>CourseDatabaseService.java</code> 和Course对象相关的数据处理类
 *
 * @author never
 * @version 2013-10-10
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class CourseDatabaseService extends UnicastRemoteObject implements DatabaseService{

	/**构造方法
	 * @throws RemoteException
	 */
	protected CourseDatabaseService() throws RemoteException {
		super();
	}

	/**
	 * Title: find
	 * Description: 通过id查询CoursePO
	 * @author zhuyuanfu
	 * @param int
	 * @return (StudentPO)PO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(int)
	 */
	@Override
	public PO find(int id) throws RemoteException {
		CoursePO ret = null;
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "select * from course where cno = '"+id+"'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				CoursePO cpo = new  CoursePO(Integer.parseInt(rs.getString("cno")),
						rs.getString("name"),
						CourseType.公选课,
						rs.getString("courseIntroduction"),
						rs.getString("time"),
						rs.getString("place"),
						rs.getInt("credit"),
						rs.getString("startTime"));
//				if(rs.getString("coursetype").equals("must-study")){
//					cpo.setCourseType(CourseType.指选课);
//				}
//				if(rs.getString("coursetype").equals("select-study")){
//					cpo.setCourseType(CourseType.公选课);
//				}
				cpo.setCourseType(CourseType.valueOf(rs.getString("courseType")));
				
				String tempSQL = "select distinct tno,name from teacher where tno in " +
						"( select tno from tc where cno = '"+rs.getString("cno")+"' )";
				Statement tempSTMT = DBManip.getConn().createStatement();
				ResultSet tempRS = tempSTMT.executeQuery(tempSQL);
				ArrayList<String> teacherNameList = new ArrayList<String>();
				ArrayList<Integer> teacherIdList = new ArrayList<Integer>();
				while(tempRS.next()){
					teacherNameList.add(tempRS.getString("name"));
					teacherIdList.add(tempRS.getInt("tno"));
				}
				cpo.setTeacherNameList(teacherNameList);
				cpo.setTeacherIdList(teacherIdList);
				
				ret = cpo;
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return ret;
	}

	/**
	 * Title: find
	 * Description:通过关键字查询CoursePO
	 * @author zhuyuanfu
	 * @param String
	 * @return ArrayList<PO>
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(java.lang.String)
	 */
	@Override
	public ArrayList<PO> find(String key) throws RemoteException {
		// key可以是course的任意属性，所以要遍历所有的属性来查看是否匹配
		ArrayList<PO> ret = new ArrayList<PO>();
		ret.clear();
		DBManip.connect();
		try{
			Statement stmt = DBManip.conn.createStatement();
			String sql = "select * from course where cno like '%"+key+"%'"
					+" or name like '%"+key+"%'"
//					+" or lecturer like '%"+key+"%'"
					+" or coursetype like '%"+key+"%'"
					+" or time like '%"+key+"%'"
					+" or place like '%"+key+"%'"
					+" or credit like '%"+key+"%'"
					+" or courseintroduction like '%"+key+"%'"
					+" or starttime like '%"+key+"%'"
					;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				CoursePO tempCoursePO = new  CoursePO(Integer.parseInt(rs.getString("cno")),
						rs.getString("name"),
						CourseType.公选课,
						rs.getString("courseintroduction"),
						rs.getString("time"),
						rs.getString("place"),
						Integer.parseInt(rs.getString("credit")),
						rs.getString("starttime"));
//				if(rs.getString("coursetype").equals("must-study")){
//					cpo.setCourseType(CourseType.指选课);
//				}
//				if(rs.getString("coursetype").equals("select-study")){
//					cpo.setCourseType(CourseType.公选课);
//				}
				tempCoursePO.setCourseType(CourseType.valueOf(rs.getString("courseType")));
				
				String tempSQL = "select distinct tno,name from teacher where tno in " +
						"( select tno from tc where cno = "+rs.getString("cno")+" )";
				System.out.println(tempSQL);
				Statement tempSTMT = DBManip.getConn().createStatement();
				ResultSet tempRS = tempSTMT.executeQuery(tempSQL);
				ArrayList<String> teacherNameList = new ArrayList<String>();
				ArrayList<Integer> teacherIdList = new ArrayList<Integer>();
				while(tempRS.next()){
					teacherNameList.add(tempRS.getString("name"));
					teacherIdList.add(tempRS.getInt("tno"));
				}
				tempCoursePO.setTeacherNameList(teacherNameList);
//				tempSQL = "select distinct tno from teacher where tno in " +
//						"( select tno from tc where cno = "+rs.getString("cno")+" )";
//				tempSTMT = DBManip.getConn().createStatement();
//				tempRS = tempSTMT.executeQuery(tempSQL);
//				
//				while(tempRS.next()){
//					teacherIdList.add(Integer.parseInt(tempRS.getString("tno")));
//				}
				tempCoursePO.setTeacherIdList(teacherIdList);
				
				ret.add(tempCoursePO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		DBManip.close();
		return ret;
	}

	/**
	 * Title: findAll
	 * Description:查询所有的CoursePO
	 * @author zhuyuanfu
	 * @param no param
	 * @return ArrayList<PO>
	 * @see com.example.cssnwu.databaseservice.DatabaseService#findAll()
	 */
	@Override
	public ArrayList<PO> findAll() throws RemoteException {
		ArrayList<PO> ret = new ArrayList<PO>();
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "select * from course";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				CoursePO tempCoursePO = new CoursePO(Integer.parseInt(rs.getString("cno")),
						rs.getString("name"),
						CourseType.指选课,
						rs.getString("courseintroduction"),
						rs.getString("time"),
						rs.getString("place"),
						Integer.parseInt(rs.getString("credit")),
						rs.getString("starttime"));
//				if(rs.getString("coursetype").equals("must-study")){
//					cpo.setCourseType(CourseType.指选课);
//				}
//				if(rs.getString("coursetype").equals("select-study")){
//					cpo.setCourseType(CourseType.公选课);
//				}
				tempCoursePO.setCourseType(CourseType.valueOf(rs.getString("courseType")));
				String tempSQL = "select distinct tno,name from teacher where tno in " +
						"( select tno from tc where cno = "+rs.getString("cno")+" )";
				System.out.println(tempSQL);
				Statement tempSTMT = DBManip.getConn().createStatement();
				ResultSet tempRS = tempSTMT.executeQuery(tempSQL);
				ArrayList<String> teacherNameList = new ArrayList<String>();
				ArrayList<Integer> teacherIdList = new ArrayList<Integer>();
				while(tempRS.next()){
					teacherNameList.add(tempRS.getString("name"));
					teacherIdList.add(tempRS.getInt("tno"));
				}
				tempCoursePO.setTeacherNameList(teacherNameList);
				
//				tempSQL = "select distinct tno from teacher where tno in " +
//						"( select tno from tc where cno = "+rs.getString("cno")+" )";
//				tempSTMT = DBManip.getConn().createStatement();
//				tempRS = tempSTMT.executeQuery(tempSQL);
//				
//				while(tempRS.next()){
//					teacherIdList.add(Integer.parseInt(tempRS.getString("tno")));
//				}
				tempCoursePO.setTeacherIdList(teacherIdList);
				
				ret.add(tempCoursePO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		System.out.println("This is the method located at CourseDatabaseService.findAll();. Find "+ ret.size()+" courses.");
		return ret;
	}

	/**
	 * Title: findAll
	 * Description: 通过id的一部分进行模糊查找，适用于记不清课程号的情况，返回所有符合的课程
	 * @author zhuyuanfu
	 * @param id
	 * @return ArrayList<PO>
	 * @see com.example.cssnwu.databaseservice.DatabaseService#findAll(int)
	 */
	@Override
	public ArrayList<PO> findAll(int id) throws RemoteException {
		ArrayList<PO> ret = new ArrayList<PO>();
		DBManip.connect();
		try{
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "select * from course where cno like  '%"+ id+"%'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				CoursePO cpo = new CoursePO(Integer.parseInt(rs.getString("cno")),
						rs.getString("name"),
						CourseType.指选课,
						rs.getString("courseintroduction"),
						rs.getString("time"),
						rs.getString("place"),
						rs.getInt("credit"),
						rs.getString("starttime"));
//				if(rs.getString("coursetype").equals("must-study")){
//					cpo.setCourseType(CourseType.指选课);
//				}
//				if(rs.getString("coursetype").equals("select-study")){
//					cpo.setCourseType(CourseType.公选课);
//				}
				cpo.setCourseType(CourseType.valueOf(rs.getString("courseType")));
				
				String tempSQL = "select distinct tno,name from teacher where tno in " +
						"( select tno from tc where cno = "+rs.getString("cno")+" )";
				Statement tempSTMT = DBManip.getConn().createStatement();
				ResultSet tempRS = tempSTMT.executeQuery(tempSQL);
				ArrayList<String> teacherNameList = new ArrayList<String>();
				ArrayList<Integer> teacherIdList = new ArrayList<Integer>();
				while(tempRS.next()){
					teacherNameList.add(tempRS.getString("name"));
					teacherIdList.add(tempRS.getInt("tno"));
				}
				cpo.setTeacherNameList(teacherNameList);
				
//				tempSQL = "select distinct tno from teacher where tno in " +
//						"( select tno from tc where cno = "+rs.getString("cno")+" )";
//				tempSTMT = DBManip.getConn().createStatement();
//				tempRS = tempSTMT.executeQuery(tempSQL);
//				
//				while(tempRS.next()){
//					teacherIdList.add(Integer.parseInt(tempRS.getString("tno")));
//				}
				cpo.setTeacherIdList(teacherIdList);
				ret.add(cpo);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * Title: update
	 * Description:更新PO对象，返回修改成功
	 * @author zhuyuanfu
	 * @param PO
	 * @return boolean 
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean update(PO po) throws RemoteException {
		if(po==null){
			System.out.println("PO是空的！空的插个毛线！负分滚粗！");
			return false;
		}
		
		
		boolean updateSuccess = false;
		DBManip.connect();
		try{
			//更新课程基本信息
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "update course set "+
						" cno = '"+((CoursePO)po).getId()+"' , "+
						" name = '"+((CoursePO)po).getCourseName()+"' , "+
						" courseType = '"+((CoursePO)po).getCourseType()+"' , "+
						" time = '"+((CoursePO)po).getCourseTime()+"', "+
						" place = '"+((CoursePO)po).getCourseLocation()+"', "+
						" credit = "+((CoursePO)po).getCredit()+", "+
						" courseIntroduction = '"+((CoursePO)po).getCourseIntro()+"', "+
						" startTime = '"+((CoursePO)po).getEstablishTime()+"'"+
						" where cno = '" + po.getId()+"'";
			System.out.println(sql);
			stmt.execute(sql);
			//更新TC表
			if(((CoursePO)po).getTeacherIdList()!=null){
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "delete from tc where cno = '"+po.getId()+"'";
				System.out.println(tempSQL1);
				tempSTMT1.execute(tempSQL1);
				
				
				for(int i = 0;i<((CoursePO)po).getTeacherIdList().size();i++){
					Statement tempSTMT2 = DBManip.getConn().createStatement();
					String tempSQL2 = "insert into tc (tno,cno) values ('"
								+((CoursePO)po).getTeacherIdList().get(i)+"','"
								+po.getId()+"')";
					System.out.println(tempSQL2);
					tempSTMT2.execute(tempSQL2);
				}
			}
			updateSuccess =  true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return updateSuccess;
	}

	/* (non-Javadoc)
	 * Title: update
	 * Description:更新PO对象的某个属性值
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean update(PO po, String attr, Object value)
			throws RemoteException {
		if(po==null){
			System.out.println("PO是空的！空的插个毛线！负分滚粗！");
			return false;
		}
		
		boolean updateSuccess = false;
		DBManip.connect();
		try{
			//更新课程基本信息
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "update course set "+
						attr+ " = '"+String.valueOf(value)+
						"' where cno = '" + po.getId()+"'";
			System.out.println(sql);
			stmt.execute(sql);
			
			if(((CoursePO)po).getTeacherIdList()!=null){
				//更新TC表
				//清除tc表该课程的所有授课老师
				Statement tempSTMT1 = DBManip.getConn().createStatement();
				String tempSQL1 = "delete from tc where cno = '"+po.getId()+"'";
				tempSTMT1.execute(tempSQL1);
				//将课程所有授课老师依次写入tc表
				for(int i = 1;i<((CoursePO)po).getTeacherIdList().size();i++){
					Statement tempSTMT2 = DBManip.getConn().createStatement();
					String tempSQL2 = "insert into tc (tno,cno) values ('"
								+((CoursePO)po).getTeacherIdList().get(i)+"','"
								+po.getId()+"')";
					System.out.println(tempSQL2);
					tempSTMT2.execute(tempSQL2);
				}
			}
			updateSuccess =  true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return updateSuccess;
	}

	/* (non-Javadoc)
	 * Title: update
	 * Description:根据id查询到课程对象，然后登记学生成绩
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(int, java.util.HashMap)
	 */
	@Override
	public boolean update(int courseId, HashMap<Integer, Double> map)
			throws RemoteException {
		System.out.println("calling courseDatabaseService.update()");
		if(new CourseDatabaseService().find(courseId)==null){
			System.out.println("该课程不存在。。。改个蛋。。。");
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
	 * Description:插入CoursePO对象，返回操作结果（操作成功或否）
	 * @author zhuyuanfu
	 * @param PO
	 * @return boolean
	 * @see com.example.cssnwu.databaseservice.DatabaseService#insert(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean insert(PO po) throws RemoteException {
		if(po == null){
			System.out.println( "传过来的CoursePO是空的！空的插入个毛线！负分滚粗不谢！" );
			return false;
		}
		
		if(find(po.getId())!=null){
			System.out.println( "已经存在该课程的信息，不能继续插入。您可以选择删除课程信息或更新课程信息。" );
			return false;
		}
		
		boolean insertSuccessful = false;
		DBManip.connect();
		try{
			Statement stmt = DBManip.conn.createStatement();
			String sql = "insert into course (cno,name,courseType,time,place,credit,courseIntroduction,startTime )" +
					" values ('"+(po).getId()+
					"','"+((CoursePO)po).getCourseName()+
					"','"+((CoursePO)po).getCourseType()+
					"','"+((CoursePO)po).getCourseTime()+
					"','"+((CoursePO)po).getCourseLocation()+
					"','"+((CoursePO)po).getCredit()+
					"','"+((CoursePO)po).getCourseIntro()+
					"','"+((CoursePO)po).getEstablishTime()+
					"');";
			System.out.println(sql);
			stmt.execute(sql);
//			if(((CoursePO)po).getCourseType().equals(CourseType.公选课)){
//				update(po,"courseType","select-study");
//			}
//			if(((CoursePO)po).getCourseType().equals(CourseType.指选课)){
//				update(po,"courseType","must-study");
//			}
			
			//获得该课程的教师列表，插入t-c表
			ArrayList<Integer> teacherIdList = ((CoursePO)po).getTeacherIdList();
			if(teacherIdList!=null){
				for(int i = 0;i<teacherIdList.size();i++){
					Statement tempSTMT1 = DBManip.getConn().createStatement();
					String tempSQL1 = "insert into tc ( tno , cno ) values ( '"
							+ teacherIdList.get(i)+"','"
							+po.getId()+"' )";
					System.out.println(tempSQL1);
					tempSTMT1.execute(tempSQL1);
				}
			}
			System.out.println("insert success");
			insertSuccessful = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return insertSuccessful;
	}

	
	/**
	 * Title: insert
	 * Description:插入CoursePO对象（好像一直用不到？或者客户端没有这个接口？）
	 * @author zhuyuanfu
	 * @param CoursePO
	 * @return boolean
	 * @see com.example.cssnwu.databaseservice.DatabaseService#insert(com.example.cssnwu.po.PO)
	 */
	public boolean insert(CoursePO po) throws RemoteException {
		if(po == null){
			System.out.println( "传过来的CoursePO是空的！空的插入个毛线！负分滚粗不谢！" );
			return false;
		}
		
		boolean insertSuccessful = false;
		DBManip.connect();
		try{
			Statement stmt = DBManip.conn.createStatement();
			String sql = "insert into course (cno,name,time,place,credit,courseIntroduction,startTime )" +
					" values ('"+(po).getId()+
					"','"+((CoursePO)po).getCourseName()+
					"','"+((CoursePO)po).getCourseTime()+
					"','"+((CoursePO)po).getCourseLocation()+
					"','"+((CoursePO)po).getCredit()+
					"','"+((CoursePO)po).getCourseIntro()+
					"','"+((CoursePO)po).getEstablishTime()+
					"');";
			stmt.execute(sql);
//			if(((CoursePO)po).getCourseType().equals(CourseType.公选课)){
//				update(po,"courseType","select-study");
//			}
//			if(((CoursePO)po).getCourseType().equals(CourseType.指选课)){
//				update(po,"courseType","must-study");
//			}
			
			ArrayList<Integer> teacherIdList = ((CoursePO)po).getTeacherIdList();
			if(teacherIdList!=null){
				for(int i = 0;i<teacherIdList.size();i++){
					Statement tempSTMT1 = DBManip.getConn().createStatement();
					String tempSQL1 = "insert into tc ( tno , cno ) values ( '"+ teacherIdList.get(i)
							+"','"+po.getId()+"' )";
					System.out.println(tempSQL1);
					tempSTMT1.execute(tempSQL1);
				}
			}
			System.out.println("insert success");
			insertSuccessful = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManip.close();
		return insertSuccessful;
	}


	
	/**
	 * Title: delete
	 * Description:删除PO对象，返回操作结果（成功或否）
	 * @author zhuyuanfu
	 * @param PO
	 * @return 
	 * @see com.example.cssnwu.databaseservice.DatabaseService#delete(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean delete(PO po) throws RemoteException {
		if(po == null){
			System.out.println("传进一个空参数啊摔");
			return false;
		}
		boolean deleteSuccess = false;
		DBManip.connect();
		try{
			//删除课程的基本信息
			Statement stmt = DBManip.getConn().createStatement();
			String sql = "delete from course where cno = '"+po.getId()+"'";
			System.out.println(sql);
			stmt.execute(sql);
			
			//删除tc表中和该课程有关的信息
			Statement tempSTMT1 = DBManip.getConn().createStatement();
			String tempSQL1 = "delete from tc where cno = '"+po.getId()+"'";
			System.out.println(tempSQL1);
			tempSTMT1.execute(tempSQL1);
			
			//删除sc表中和该课程有关的信息
			Statement tempSTMT2 = DBManip.getConn().createStatement();
			String tempSQL2 = "delete from sc where sno = '"+po.getId()+"'";
			System.out.println(tempSQL2);
			tempSTMT2.execute(tempSQL2);
			
			deleteSuccess = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		DBManip.close();
		return deleteSuccess;
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
	
	
	/**
	 * Title： main
	 * Description：测试用
	 * @author zhuyuanfu
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException{
		CourseDatabaseService cds = new CourseDatabaseService();
		//CoursePO cpo1 = (CoursePO)cds.find(111110110);
		//System.out.println(cpo1.getEstablishTime());
		
	//	ArrayList<PO> clist1 = (cds.find("rg2"));
		//System.out.println(((CoursePO)clist1.get(0)).getCourseIntro());
		
		ArrayList<PO> clist2 = cds.findAll();
		System.out.println(clist2.size());
		System.out.println("--------------------------------------------------------");
		System.out.println(((CoursePO)clist2.get(0)).getTeacherNameList());
		System.out.println(((CoursePO)clist2.get(1)).getTeacherNameList());
		System.out.println(((CoursePO)clist2.get(2)).getTeacherNameList());
		System.out.println(((CoursePO)clist2.get(3)).getTeacherNameList());
		System.out.println("--------------------------------------------------------");
		System.out.println(((CoursePO)clist2.get(0)).getTeacherIdList());
		System.out.println(((CoursePO)clist2.get(1)).getTeacherIdList());
		System.out.println(((CoursePO)clist2.get(2)).getTeacherIdList());
		System.out.println(((CoursePO)clist2.get(3)).getTeacherIdList());
		
		System.out.println("--------------------------------------------------------");
		
//		CoursePO cpo2 = (CoursePO)cds.find(20691);
//		System.out.println(cpo2.getId());
//		System.out.println(cpo2.getCourseIntro());
//		cpo2.setCourseIntro("srhkjsksrlh");
//		cds.update(cpo2);
		
		CoursePO cpo3 = new CoursePO(20100);
		ArrayList<Integer> tnoList = new ArrayList<Integer>();
		tnoList.add(340);
		tnoList.add(341);
		tnoList.add(888);
		cpo3.setTeacherIdList(tnoList);
		
		cds.update(cpo3,"","");
		
	}

	

}
