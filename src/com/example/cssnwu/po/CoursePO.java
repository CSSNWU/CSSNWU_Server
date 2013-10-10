/**
 * @(#)CoursePO.java     	2013-10-4 下午8:00:00
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import java.util.ArrayList;

import com.example.cssnwu.businesslogicservice.resultenum.CourseType;



/**
 *Class <code>CoursePO.java</code> 课程
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class CoursePO extends PO{
    String courseName;                   //课程名称   
    CourseType courseType;               //课程类型
    String courseIntro;                  //课程介绍
    ArrayList<TeacherPO> teacherList;    //任课老师
    String courseTime;                   //上课时间
    String courseLocation;               //上课地点
    int credit;                          //学分
    double score;                        //成绩
    String establishTime;                  //开课时间
    
    
	public String getCourseName() {
		return courseName;
	}
	public CourseType getCourseType() {
		return courseType;
	}
	public String getCourseIntro() {
		return courseIntro;
	}
	public ArrayList<TeacherPO> getTeacherList() {
		return teacherList;
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
	
	//setter方法
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}
	public void setCourseIntro(String courseIntro) {
		this.courseIntro = courseIntro;
	}
	public void setTeacherList(ArrayList<TeacherPO> teacherList) {
		this.teacherList = teacherList;
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
