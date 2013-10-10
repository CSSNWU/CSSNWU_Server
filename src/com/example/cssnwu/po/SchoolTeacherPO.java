/**
 * @(#)SchoolTeacher.java     	2013-10-4 ����9:00:49
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import com.example.cssnwu.businesslogicservice.resultenum.UserType;

/**
 *Class <code>SchoolTeacher.java</code> ѧУ������ʦ
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class SchoolTeacherPO extends UserPO{

	/**
	 * @param userName    �û���
	 * @param password    ����
	 * @param isLogin     �Ƿ��½
	 * @param userType    �û�����
	 */
	public SchoolTeacherPO(String userName, String password, boolean isLogin,
			UserType userType) {
		super(userName, password, isLogin, userType);
	}
    
}
