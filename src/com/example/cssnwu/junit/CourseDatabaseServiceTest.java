package com.example.cssnwu.junit;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.example.cssnwu.database.CourseDatabaseService;
import com.example.cssnwu.database.DatabaseFactoryImpl;
import com.example.cssnwu.database.UserDatabaseService;
import com.example.cssnwu.databaseservice.DatabaseFactory;
import com.example.cssnwu.po.CoursePO;
import com.example.cssnwu.po.PO;
import com.example.cssnwu.po.TeacherPO;
import com.example.cssnwu.po.UserPO;

import junit.framework.TestCase;

public class CourseDatabaseServiceTest extends TestCase {
	DatabaseFactory databaseFactory = null;
	CourseDatabaseService courseDatabaseService = null;
	
	
    @Override
	protected void setUp() throws Exception {
		super.setUp();
		databaseFactory = new DatabaseFactoryImpl();
		courseDatabaseService = (CourseDatabaseService) databaseFactory.getCourseDatabaseService();
	}


	public void testfind() throws RemoteException{
         int courseId = 20111;
         CoursePO course = (CoursePO) courseDatabaseService.find(courseId);
         assertEquals("Èí¹¤2", course.getCourseName());
    }
	
	public void testfindAll() throws RemoteException {
		ArrayList<PO> coursePOs = courseDatabaseService.findAll();
		assertEquals(22, coursePOs.size());
	}
	
    public void testUpdate() throws RemoteException {
    	PO po = null;
    	boolean  result  = courseDatabaseService.update(null);
    	assertEquals(false, result);
    }
    
    public void testInsert() throws RemoteException {
    	PO po = null;
    	boolean result = courseDatabaseService.insert(po);
    	assertEquals(false, result);
    }
    
    public void testfindByKey() throws RemoteException{
    	String key = "C++";
    	ArrayList<PO> pos = courseDatabaseService.find(key);
    	assertEquals(1, pos.size());
    }
    
    public void testfindByKey2() throws RemoteException{
    	String key = "¹«Ñ¡¿Î";
    	ArrayList<PO> pos = courseDatabaseService.find(key);
    	assertEquals(17, pos.size());
    }
    
    
    
}
