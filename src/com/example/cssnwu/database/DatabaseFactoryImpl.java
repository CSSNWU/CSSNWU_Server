/**
 * @(#)DatabaseFactoryImp.java     	2013-10-5 下午2:06:48
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.database;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.example.cssnwu.databaseservice.DatabaseFactory;
import com.example.cssnwu.databaseservice.DatabaseService;

/**
 *Class <code>DatabaseFactoryImp.java</code> TODO
 *
 * @author never
 * @version 2013-10-5
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class DatabaseFactoryImpl extends UnicastRemoteObject implements DatabaseFactory{
    private static UserDatabaseService userDatabaseService;
    private static CourseDatabaseService courseDatabaseService;
    private static DeptPlanDatabaseService deptPlanDatabaseService;
    private static StudentDatabaseService studentDatabaseService;
    private static SchoolStrategyDatabaseService schoolStrategyDatabaseService;
    private static TeacherDatabaseService teacherDatabaseService;
	/**构造方法
	 * @throws RemoteException
	 */
	public DatabaseFactoryImpl() throws RemoteException {
		super();
	}

	/* (non-Javadoc)
	 * Title: getCourseDatabaseService
	 * Description:
	 * @see com.example.cssnwu.databaseservice.DatabaseFactory#getCourseDatabaseService()
	 */
	@Override
	public DatabaseService getCourseDatabaseService() throws RemoteException {
		if (courseDatabaseService == null) {
			courseDatabaseService = new CourseDatabaseService();
		}
		return courseDatabaseService;
	}

	/* (non-Javadoc)
	 * Title: getDeptPlanDatabaseService
	 * Description:
	 * @see com.example.cssnwu.databaseservice.DatabaseFactory#getDeptPlanDatabaseService()
	 */
	@Override
	public DatabaseService getDeptPlanDatabaseService() throws RemoteException {
		if (deptPlanDatabaseService == null) {
			deptPlanDatabaseService = new DeptPlanDatabaseService();
		}
		return deptPlanDatabaseService;
	}

	/* (non-Javadoc)
	 * Title: getSchoolStrategyDatabaseService
	 * Description:
	 * @see com.example.cssnwu.databaseservice.DatabaseFactory#getSchoolStrategyDatabaseService()
	 */
	@Override
	public DatabaseService getSchoolStrategyDatabaseService()
			throws RemoteException {
		if (schoolStrategyDatabaseService == null) {
			schoolStrategyDatabaseService = new SchoolStrategyDatabaseService();
		}
		return schoolStrategyDatabaseService;
	}

	/* (non-Javadoc)
	 * Title: getUserDatabaseService
	 * Description:
	 * @see com.example.cssnwu.databaseservice.DatabaseFactory#getUserDatabaseService()
	 */
	@Override
	public DatabaseService getUserDatabaseService() throws RemoteException {
        if(userDatabaseService == null) {
        	userDatabaseService = new UserDatabaseService();
        }
		return userDatabaseService;
	}

	/* (non-Javadoc)
	 * Title: getStudentDatabaseService
	 * Description:
	 * @see com.example.cssnwu.databaseservice.DatabaseFactory#getStudentDatabaseService()
	 */
	@Override
	public DatabaseService getStudentDatabaseService() throws RemoteException {
		if (studentDatabaseService == null) {
			studentDatabaseService = new StudentDatabaseService();
		}
		return studentDatabaseService;
	}

	/* (non-Javadoc)
	 * Title: getTeacherDatabaseService
	 * Description:
	 * @see com.example.cssnwu.databaseservice.DatabaseFactory#getTeacherDatabaseService()
	 */
	@Override
	public DatabaseService getTeacherDatabaseService() throws RemoteException {
		if (teacherDatabaseService == null) {
			teacherDatabaseService = new TeacherDatabaseService();
		}
		return teacherDatabaseService;
	}

}
