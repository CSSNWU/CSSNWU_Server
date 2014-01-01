package com.example.cssnwu.junit;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.example.cssnwu.database.CourseDatabaseService;
import com.example.cssnwu.database.DatabaseFactoryImpl;
import com.example.cssnwu.database.DeptPlanDatabaseService;
import com.example.cssnwu.databaseservice.DatabaseFactory;
import com.example.cssnwu.po.PO;
import com.example.cssnwu.po.TeacherPO;

import junit.framework.TestCase;

public class DeptPlanDatabaseServiceTest extends TestCase {
	DatabaseFactory databaseFactory = null;
	DeptPlanDatabaseService deptPlanDatabaseService = null;
	
	
    @Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		databaseFactory = new DatabaseFactoryImpl();
		deptPlanDatabaseService = (DeptPlanDatabaseService) databaseFactory.getDeptPlanDatabaseService();
	}
    public void testfind() throws RemoteException {
	String key = "Èí¼þÑ§Ôº";
	ArrayList<PO> deptPlans = deptPlanDatabaseService.find(key);
	assertEquals(0, deptPlans.size());
    }
    
    public void testfindall() throws RemoteException {
    	ArrayList<PO> deptPlans = deptPlanDatabaseService.findAll();
    	assertEquals(2, deptPlans.size());
    }

}
