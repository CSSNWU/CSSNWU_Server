/**
 * @(#)StudentPO.java     	2013-10-4 ����8:36:46
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import java.util.ArrayList;

import com.example.cssnwu.businesslogicservice.resultenum.Department;
import com.example.cssnwu.businesslogicservice.resultenum.StudentType;
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
	Department department;           	//����Ժϵ
    String grade;                     	//�꼶
    double gpa;                       	//ѧ�ּ�
    int year;						  	//��ѧ���
    ArrayList<CoursePO> courseList;   	//�γ��б�
//    ArrayList<Integer> courseIdList;  //�γ̱���б�
    Department targetDepartment;      	//Ŀ��Ժϵ����תԺ�����ѧ����һ��ѧ��������Ϊ�գ�
    StudentType studentType;			//ѧ�����ͣ�StudentType.StayDown��Transfer��Graduate��Drop��
    
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
    public StudentPO(int id,String userName, String password, boolean isLogin,
			UserType userType) {
		super(userName, password, isLogin, userType);
		super.id = id;
	}
    
    
    //getter����
    /**
     *	���ѧ������
     *	@author zhuyuanfu
     *	@param null
     *	@return StudentType
     *
     */
    public StudentType getStudentType(){
    	return this.studentType;
    }
    
    /**
     * �����ѧ���
     * @return
     */
    public int getYearIntoSchool(){
    	return year;
    }
    /**
     *	���ѧ������Ժϵ
     *	@author zhuyuanfu
     *	@param null
     *	@return Department
     *
     */
	public Department getDepartment() {
		return department;
	}
	
	/**
     *	���ѧ���꼶
     *	@author zhuyuanfu
     *	@param null
     *	@return String
     *
     */
	public String getGrade() {
		return grade;
	}
	
	/**
     *	���ѧ��GPA
     *	@author zhuyuanfu
     *	@param null
     *	@return double
     *
     */
	public double getGpa() {
		return gpa;
	}
//	public ArrayList<String> getCourseNameList() {
//		return courseNameList;
//	}
//	public ArrayList<Integer> getCourseIdList() {
//		return courseIdList;
//	}
	
	/**
     *	���ѧ��Ŀ��Ժϵ
     *	@author zhuyuanfu
     *	@param null
     *	@return Department
     *
     */
	public Department getTargetDepartment() {
		return targetDepartment;
	}
	
	/**
     *	���ѧ��ѡ���б�
     *	@author zhuyuanfu
     *	@param null
     *	@return StudentType
     *
     */
	public ArrayList<CoursePO> getCoursePOList(){
		return this.courseList;
	}
	
	//setter
	
	/**
     *	����ѧ���γ��б�
     *	@author zhuyuanfu
     *	@param ArrayList<CoursePO>
     *	@return null
     *
     */
	public void setCoursePOList(ArrayList<CoursePO> a){
		this.courseList = a;
	}
	
	/**
     *	����ѧ������Ժϵ
     *	@author zhuyuanfu
     *	@param 
     *	@return null
     *
     */
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	/**
     *	����ѧ���꼶
     *	@author zhuyuanfu
     *	@param String
     *	@return null
     *
     */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	/**
     *	����ѧ��GPA
     *	@author zhuyuanfu
     *	@param double
     *	@return null
     *
     */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
     *	����ѧ����ѧ���
     *	@author zhuyuanfu
     *	@param int
     *	@return null
     *
     */
	public void setYearIntoSchool(int year){
		this.year=year;
	}
//	public void setCourseNameList(ArrayList<String> courseNameList) {
//		this.courseNameList = courseNameList;
//	}
//	public void setCourseIdList(ArrayList<Integer> courseIdList) {
//		this.courseIdList = courseIdList;
//	}
	
	/**
     *	����ѧ��Ŀ��Ժϵ
     *	@author zhuyuanfu
     *	@param Department
     *	@return null
     *
     */
	public void setTargetDepartment(Department targetDepartment) {
		this.targetDepartment = targetDepartment;
	}


	/**
     *	����ѧ������
     *	@author zhuyuanfu
     *	@param StudentType
     *	@return null
     *
     */
	public void setStudentType(StudentType studentType){
		this.studentType = studentType;
	}
	
}
