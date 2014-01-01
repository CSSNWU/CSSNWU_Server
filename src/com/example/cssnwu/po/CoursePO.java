/**
 * @(#)CoursePO.java     	2013-10-4 ����8:00:00
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import java.util.ArrayList;

import com.example.cssnwu.businesslogicservice.resultenum.CourseType;



/**
 *Class <code>CoursePO.java</code> �γ�
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class CoursePO extends PO{
    String courseName;                   //�γ�����   15651819563
    CourseType courseType;               //�γ�����
    String courseIntro;                  //�γ̽���
    ArrayList<String> teacherNameList;	 //�ο���ʦ�����б�                    
    ArrayList<Integer> teacherIDList;    //�ο���ʦ�����б�
    String courseTime;                   //�Ͽ�ʱ�� (��ʽ�����ܼ��� + "/" +  ��1~3��)
    String courseLocation;               //�Ͽεص� (��ʽ��"У��" + "/" + "����")
    int credit;                          //ѧ��
    double score;                       //�ɼ�
    String establishTime;                //����ʱ��
    
    public CoursePO(){
    	
    }
    
    public CoursePO(int cno){
    	super.id = cno;
    }
    
    public CoursePO(int cno,String cName,CourseType cType,String cIntro,
    		String cTime,String cLocation,int cCredit,String cStartTime){
    	super.id = cno;
    	this.courseName = cName;
    	this.courseType = cType;
    	this.courseIntro = cIntro;
    	this.courseTime = cTime;
    	this.courseLocation = cLocation;
    	this.credit = cCredit;
    	this.establishTime = cStartTime;
    	
    }
	public String getCourseName() {
		return courseName;
	}
	public CourseType getCourseType() {
		return courseType;
	}
	public String getCourseIntro() {
		return courseIntro;
	}
	public ArrayList<Integer> getTeacherIdList() {
		return teacherIDList;
	}
	public ArrayList<String> getTeacherNameList()
	{
		return teacherNameList;
	}
	public String getCourseTime() {
		return courseTime;
	}
	public String getCourseLocation() {
		return courseLocation;
	}
	public int getCredit() {
		return credit;
	}
	public double getScore() {
		return score;
	}
	public String getEstablishTime() {
		return establishTime;
	}
	
	//setter����
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}
	public void setCourseIntro(String courseIntro) {
		this.courseIntro = courseIntro;
	}
	public void setTeacherIdList(ArrayList<Integer> teacherList) {
		this.teacherIDList = teacherList;
	}
	public void setTeacherNameList(ArrayList<String> teacherNameList){
		this.teacherNameList=teacherNameList;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public void setEstablishTime(String establishTime) {
		this.establishTime = establishTime;
	}
} 