/**
 * @(#)StudentPO.java     	2013-10-4 ����8:36:46
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import java.util.ArrayList;

import com.example.cssnwu.businesslogicservice.resultenum.Department;
import com.example.cssnwu.businesslogicservice.resultenum.UserType;

/**
 *Class <code>StudentPO.java</code> ѧ��
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class StudentPO extends UserPO{
	Department department;            //����Ժϵ
    String grade;                     //�꼶
    double gpa;                       //ѧ�ּ�
    ArrayList<CoursePO> courseList;   //�γ��б�
    Department targetDepartment;      //Ŀ��Ժϵ����תԺ�����ѧ����һ��ѧ��������Ϊ�գ�
    
    /**���췽��
	 * @param userName    
	 * @param password
	 * @param isLogin
	 * @param userType
	 */
	public StudentPO(String userName, String password, boolean isLogin,
			UserType userType) {
		super(userName, password, isLogin, userType);
	}
    
    //getter����
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
