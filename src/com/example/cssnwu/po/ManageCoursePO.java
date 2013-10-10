/**
 * @(#)SelectCoursePO.java     	2013-10-4 下午9:02:28
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import java.util.ArrayList;
import java.util.Date;

import com.example.cssnwu.businesslogicservice.resultenum.ManageCourseType;

/**
 *Class <code>SelectCoursePO.java</code> 课程管理
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class ManageCoursePO extends PO{
    StudentPO studentPO;                 //管理课程学生
    Date date;                           //管理课程时间
    ManageCourseType manageCourseType;   //管理课程类型（选课/退选）
    ArrayList<CoursePO> courseList;      //管理课程列表
    
    //getter方法
	public StudentPO getStudentPO() {
		return studentPO;
	}
	public Date getDate() {
		return date;
	}
	public ManageCourseType getManageCourseType() {
		return manageCourseType;
	}
	public ArrayList<CoursePO> getCourseList() {
		return courseList;
	}
    
}
