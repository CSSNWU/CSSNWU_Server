/**
 * @(#)DeptTeacherPO.java     	2013-10-4 ����8:50:18
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import com.example.cssnwu.businesslogicservice.resultenum.Department;
import com.example.cssnwu.businesslogicservice.resultenum.UserType;

/**
 *Class <code>DeptTeacherPO.java</code> Ժϵ������ʦ
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class DeptTeacherPO extends UserPO{
	Department department;         //Ժϵ

    /**
	 * @param userName   �û���
	 * @param password   ����
	 * @param isLogin    �Ƿ��½
	 * @param userType   �û�����
	 */
	public DeptTeacherPO(String userName, String password, boolean isLogin,
			UserType userType) {
		super(userName, password, isLogin, userType);
	}
	
    /**
	 * @param userName   �û���
	 * @param password   ����
	 * @param isLogin    �Ƿ��½
	 * @param userType   �û�����
	 * @param department Ժϵ
	 */
	public DeptTeacherPO(String userName, String password, boolean isLogin,
			UserType userType,Department department) {
	 	super(userName, password, isLogin, userType);
        this.department = department;
	}
    //getter����
	public Department getDepartment() {
		return department;
	}

}
