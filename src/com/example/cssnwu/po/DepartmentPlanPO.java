/**
 * @(#)DepartmentPlanPO.java     	2013-10-4 ����10:04:06
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import java.util.ArrayList;

import com.example.cssnwu.businesslogicservice.resultenum.Department;

/**
 *Class <code>DepartmentPlanPO.java</code> Ժϵ��ѧ�ƻ�
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class DepartmentPlanPO extends	 PO{
	Department department;              			//Ժϵ
    ArrayList<CoursePO> courseList;     			//�γ̰���
    public int minCourseCredits[] = new int[4];	//ÿ�����ѧ��
    //getter����
	
	public Department getDepartment(){
		return department;
	}
	
	public ArrayList<CoursePO> getCourseList() {
		return courseList;
	}

	
    //setter����
	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setCourseList(ArrayList<CoursePO> courseList) {
		this.courseList = courseList;
	}
	
}
