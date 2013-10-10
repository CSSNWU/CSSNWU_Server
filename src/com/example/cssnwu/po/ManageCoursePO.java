/**
 * @(#)SelectCoursePO.java     	2013-10-4 ����9:02:28
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import java.util.ArrayList;
import java.util.Date;

import com.example.cssnwu.businesslogicservice.resultenum.ManageCourseType;

/**
 *Class <code>SelectCoursePO.java</code> �γ̹���
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class ManageCoursePO extends PO{
    StudentPO studentPO;                 //����γ�ѧ��
    Date date;                           //����γ�ʱ��
    ManageCourseType manageCourseType;   //����γ����ͣ�ѡ��/��ѡ��
    ArrayList<CoursePO> courseList;      //����γ��б�
    
    //getter����
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
