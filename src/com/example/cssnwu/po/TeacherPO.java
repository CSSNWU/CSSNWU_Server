/**
 * @(#)TeacherPO.java     	2013-10-4 下午8:47:48
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import java.util.ArrayList;

import com.example.cssnwu.businesslogicservice.resultenum.Department;
import com.example.cssnwu.businesslogicservice.resultenum.UserType;

/**
 *Class <code>TeacherPO.java</code> 任课老师
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class TeacherPO extends UserPO{
	Department department;                //院系（部门）
    ArrayList<Integer> courseList;       //开课课程编号列表
    
    ArrayList<CoursePO> coursePOList;
    
    /**构造方法
	 * @param userName   用户名
	 * @param password   密码
	 * @param isLogin    登陆状态
	 * @param userType   用户类型
	 */
	public TeacherPO(String userName, String password, boolean isLogin,
			UserType userType) {
		super(userName, password, isLogin, userType);
	}
	
	/**
	 * 构造方法2
	 * @param userName     用户名
	 * @param password     密码
	 * @param isLogin      登陆状态
	 * @param userType     用户类型
	 * @param department   院系
	 * @param courseList   课程列表
	 */
	public TeacherPO(String userName, String password, boolean isLogin,
			UserType userType,Department department,ArrayList<Integer> courseList) {
		super(userName, password, isLogin, userType);
		this.department = department;
		this.courseList = courseList;
	}
	
	/**
	 * 构造方法3（空构造方法）
	 * 
	 */
	public TeacherPO(){
		
	}
	/**
	 * 构造方法4
	 * @param id	工号
	 * 
	 * */
	public TeacherPO(int id){
		this.id = id;
	}
	
    //getter方法
	public Department getDepartment() {
		return department;
	}
	public ArrayList<Integer> getCourseList() {
		return courseList;
	}
	public ArrayList<CoursePO> getCoursePOList(){
		return this.coursePOList;
	}

	//setter方法
	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setCourseList(ArrayList<Integer> courseList) {
		this.courseList = courseList;
	}
	public void setCoursePOList(ArrayList<CoursePO> cPOList){
		this.coursePOList = cPOList;
	}
	
}
