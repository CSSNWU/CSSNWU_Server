/**
 * @(#)SchoolStrategyPO.java     	2013-10-4 ����9:49:02
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;


/**
 *Class <code>SchoolStrategyPO.java</code> ѧУ�����ܲ���
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class SchoolStrategyPO extends PO{
    int totalCredit;            //��ѧ��
    int[] minCreditPerSeason;   //ÿѧ�����ѧ��Ҫ��
    
    //getter����
	public int getTotalCredit() {
		return totalCredit;
	}
	public int[] getMinCreditPerSeason() {
		return minCreditPerSeason;
	}
	
	//setter����
	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}
	public void setMinCreditPerSeason(int[] minCreditPerSeason) {
		this.minCreditPerSeason = minCreditPerSeason;
	}
    
	
}
