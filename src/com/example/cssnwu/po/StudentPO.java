/**
 * @(#)StudentPO.java     	2013-10-4 下午8:36:46
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import java.util.ArrayList;

import com.example.cssnwu.businesslogicservice.resultenum.Department;
import com.example.cssnwu.businesslogicservice.resultenum.UserType;

/**
 *Class <code>StudentPO.java</code> 学生
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class StudentPO extends UserPO{
	Department department;            //所在院系
    String grade;                     //年级
    double gpa;                       //学分绩
    ArrayList<CoursePO> courseList;   //课程列表
    Department targetDepartment;      //目标院系（有转院需求的学生，一般学生该属性为空）
    
    /**构造方法
	 * @param userName    
	 * @param password
	 * @param isLogin
	 * @param userType
	 */
	public StudentPO(String userName, String password, boolean isLogin,
			UserType userType) {
		super(userName, password, isLogin, userType);
	}
    
    //getter方法
	public Department getDepartment() {
		return department;
	}
	public String getGrade() {
		return grade;
	}
	public double getGpa() {
		return gpa;
	}
	public ArrayList<CoursePO> getCourseList() {
		return courseList;
	}
	public Department getTargetDepartment() {
		return targetDepartment;
	}
	
	
}
