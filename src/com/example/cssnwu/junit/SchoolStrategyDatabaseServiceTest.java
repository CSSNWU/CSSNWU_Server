package com.example.cssnwu.junit;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.example.cssnwu.database.DatabaseFactoryImpl;
import com.example.cssnwu.database.SchoolStrategyDatabaseService;
import com.example.cssnwu.databaseservice.DatabaseFactory;
import com.example.cssnwu.po.PO;
import com.example.cssnwu.po.SchoolStrategyPO;

import junit.framework.TestCase;

public class SchoolStrategyDatabaseServiceTest extends TestCase {
	DatabaseFactory databaseFactory = null;
	SchoolStrategyDatabaseService schoolStrategyDatabaseService = null;
	
	
    @Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		databaseFactory = new DatabaseFactoryImpl();
		schoolStrategyDatabaseService = (SchoolStrategyDatabaseService) databaseFactory.getSchoolStrategyDatabaseService();
	}


	public void testfind() throws RemoteException{
        ArrayList<PO> schoolStrategys = schoolStrategyDatabaseService.findAll();
        assertEquals(3, schoolStrategys.size());
    }
	
	public void testfindByYear() throws RemoteException {
		int year = 2013;
		PO pos = schoolStrategyDatabaseService.find(year);
		assertEquals(4, ((SchoolStrategyPO)pos).getMinCreditPerSeason().length);
	}
}
