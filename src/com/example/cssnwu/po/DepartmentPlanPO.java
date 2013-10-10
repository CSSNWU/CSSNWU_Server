/**
 * @(#)DepartmentPlanPO.java     	2013-10-4 下午10:04:06
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import java.util.ArrayList;
import java.util.Date;

import com.example.cssnwu.businesslogicservice.resultenum.Department;

/**
 *Class <code>DepartmentPlanPO.java</code> 院系教学计划
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class DepartmentPlanPO extends PO{
	Date date;                           //年份
	Department department;               //院系
    ArrayList<CoursePO> courseList;      //课程安排
    
    //getter方法
	public Date getDate() {
		return date;
	}
	
	public Department getDepartment(){
		return department;
	}
	
	public ArrayList<CoursePO> getCourseList() {
		return courseList;
	}

	
	//setter方法
	public void setDate(Date date) {
		this.date = date;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setCourseList(ArrayList<CoursePO> courseList) {
		this.courseList = courseList;
	}
	
}
