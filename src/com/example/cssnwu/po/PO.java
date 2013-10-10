/**
 * @(#)PO.java     	2013-10-4 下午8:11:23
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.po;

import java.io.Serializable;

/**
 *Class <code>PO.java</code> 基类
 *
 * @author never
 * @version 2013-10-4
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class PO implements Serializable{
    int id;

    //getter方法
	public int getId() {
		return id;
	}

	//setter方法
	public void setId(int id) {
		this.id = id;
	}
    
    
}
