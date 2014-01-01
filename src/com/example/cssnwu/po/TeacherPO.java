/**
 * @(#)TeacherPO.java     	2013-10-4 ����8:47:48
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import java.util.ArrayList;

import com.example.cssnwu.businesslogicservice.resultenum.Department;
import com.example.cssnwu.businesslogicservice.resultenum.UserType;

/**
 *Class <code>TeacherPO.java</code> �ο���ʦ
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class TeacherPO extends UserPO{
	Department department;                //Ժϵ�����ţ�
    ArrayList<Integer> courseList;       //���ογ̱���б�
    
    ArrayList<CoursePO> coursePOList;
    
    /**���췽��
	 * @param userName   �û���
	 * @param password   ����
	 * @param isLogin    ��½״̬
	 * @param userType   �û�����
	 */
	public TeacherPO(String userName, String password, boolean isLogin,
			UserType userType) {
		super(userName, password, isLogin, userType);
	}
	
	/**
	 * ���췽��2
	 * @param userName     �û���
	 * @param password     ����
	 * @param isLogin      ��½״̬
	 * @param userType     �û�����
	 * @param department   Ժϵ
	 * @param courseList   �γ��б�
	 */
	public TeacherPO(String userName, String password, boolean isLogin,
			UserType userType,Department department,ArrayList<Integer> courseList) {
		super(userName, password, isLogin, userType);
		this.department = department;
		this.courseList = courseList;
	}
	
	/**
	 * ���췽��3���չ��췽����
	 * 
	 */
	public TeacherPO(){
		
	}
	/**
	 * ���췽��4
	 * @param id	����
	 * 
	 * */
	public TeacherPO(int id){
		this.id = id;
	}
	
    //getter����
	public Department getDepartment() {
		return department;
	}
	public ArrayList<Integer> getCourseList() {
		return courseList;
	}
	public ArrayList<CoursePO> getCoursePOList(){
		return this.coursePOList;
	}

	//setter����
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
