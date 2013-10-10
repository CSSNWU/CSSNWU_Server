/**
 * @(#)DeptTeacherPO.java     	2013-10-4 下午8:50:18
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import com.example.cssnwu.businesslogicservice.resultenum.Department;
import com.example.cssnwu.businesslogicservice.resultenum.UserType;

/**
 *Class <code>DeptTeacherPO.java</code> 院系教务老师
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class DeptTeacherPO extends UserPO{
	Department department;         //院系

    /**
	 * @param userName   用户名
	 * @param password   密码
	 * @param isLogin    是否登陆
	 * @param userType   用户类型
	 */
	public DeptTeacherPO(String userName, String password, boolean isLogin,
			UserType userType) {
		super(userName, password, isLogin, userType);
	}
	
    /**
	 * @param userName   用户名
	 * @param password   密码
	 * @param isLogin    是否登陆
	 * @param userType   用户类型
	 * @param department 院系
	 */
	public DeptTeacherPO(String userName, String password, boolean isLogin,
			UserType userType,Department department) {
	 	super(userName, password, isLogin, userType);
        this.department = department;
	}
    //getter方法
	public Department getDepartment() {
		return department;
	}

}
