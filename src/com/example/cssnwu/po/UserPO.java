/**
 * @(#)UserPO.java     	2013-10-4 下午8:34:59
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import com.example.cssnwu.businesslogicservice.resultenum.UserType;

/**
 *Class <code>UserPO.java</code> 用户基类
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class UserPO extends PO{
    String userName;          //用户名
    String password;          //密码
    boolean isLogin;          //是否登陆
    UserType userType;        //用户类型
    
    //构造方法
    public UserPO(){
    	super();
    }
    
    //构造方法
    public UserPO(String userName, String password, boolean isLogin,
			UserType userType) {
		super();
		this.userName = userName;
		this.password = password;
		this.isLogin = isLogin;
		this.userType = userType;
	}
	//getter方法
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public boolean isLogin() {
		return isLogin;
	}
	public UserType getUserType() {
		return userType;
	}
	
	//setter方法
	public void setUserName(String a){
		this.userName = a;
	}
	public void setPassword(String a){
		this.password = a;
	}
	public void setIsLogin(boolean a){
		this.isLogin = a;
	}
	public void setUserType(UserType a){
		this.userType = a;
	}
    
}
