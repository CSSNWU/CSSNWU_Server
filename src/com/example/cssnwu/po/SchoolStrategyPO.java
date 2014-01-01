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
    int[] minCreditPerSeason;   //每学年最低学分要求
    
    //getter方法
	public int getTotalCredit() {
		return totalCredit;
	}
	public int[] getMinCreditPerSeason() {
		return minCreditPerSeason;
	}
	
	//setter方法
	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}
	public void setMinCreditPerSeason(int[] minCreditPerSeason) {
		this.minCreditPerSeason = minCreditPerSeason;
	}
    
	
}
