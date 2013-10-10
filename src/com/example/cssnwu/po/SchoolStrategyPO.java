/**
 * @(#)SchoolStrategyPO.java     	2013-10-4 下午9:49:02
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;


/**
 *Class <code>SchoolStrategyPO.java</code> 学校主体框架策略
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class SchoolStrategyPO extends PO{
    int totalCredit;            //总学分
    int minCreditPerSemester;   //每学期最低学分要求
    String creditArrangeMent;   //学分安排
    
    //getter方法
	public int getTotalCredit() {
		return totalCredit;
	}
	public int getMinCreditPerSemester() {
		return minCreditPerSemester;
	}
	public String getCreditArrangeMent() {
		return creditArrangeMent;
	}
    
}
