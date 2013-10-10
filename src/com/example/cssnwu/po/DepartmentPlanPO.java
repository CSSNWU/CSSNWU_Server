/**
 * @(#)DepartmentPlanPO.java     	2013-10-4 ����10:04:06
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import java.util.ArrayList;
import java.util.Date;

import com.example.cssnwu.businesslogicservice.resultenum.Department;

/**
 *Class <code>DepartmentPlanPO.java</code> Ժϵ��ѧ�ƻ�
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class DepartmentPlanPO extends PO{
	Date date;                           //���
	Department department;               //Ժϵ
    ArrayList<CoursePO> courseList;      //�γ̰���
    
    //getter����
	public Date getDate() {
		return date;
	}
	
	public Department getDepartment(){
		return department;
	}
	
	public ArrayList<CoursePO> getCourseList() {
		return courseList;
	}

	
	//setter����
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
