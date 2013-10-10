/**
 * @(#)DatabaseFactory.java     	2013-10-5 上午9:55:06
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.databaseservice;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *Class <code>DatabaseFactory.java</code> 数据库的抽象工厂
 *
 * @author never
 * @version 2013-10-5
 * @since JDK1.7
 */
public interface DatabaseFactory extends Remote,Serializable{
    public DatabaseService getCourseDatabaseService() throws RemoteException;
    public DatabaseService getDeptPlanDatabaseService() throws RemoteException;
    public DatabaseService getSchoolStrategyDatabaseService() throws RemoteException;
    public DatabaseService getUserDatabaseService() throws RemoteException;
    public DatabaseService getStudentDatabaseService() throws RemoteException;
    public DatabaseService getTeacherDatabaseService() throws RemoteException;    
}
