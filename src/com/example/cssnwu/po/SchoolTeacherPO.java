/**
 * @(#)SchoolTeacher.java     	2013-10-4 下午9:00:49
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import com.example.cssnwu.businesslogicservice.resultenum.UserType;

/**
 *Class <code>SchoolTeacher.java</code> 学校教务老师
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class SchoolTeacherPO extends UserPO{

	/**
	 * @param userName    用户名
	 * @param password    密码
	 * @param isLogin     是否登陆
	 * @param userType    用户类型
	 */
	public SchoolTeacherPO(String userName, String password, boolean isLogin,
			UserType userType) {
		super(userName, password, isLogin, userType);
	}
    
}
