package com.example.cssnwu.junit;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.example.cssnwu.database.DatabaseFactoryImpl;
import com.example.cssnwu.database.StudentDatabaseService;
import com.example.cssnwu.database.UserDatabaseService;
import com.example.cssnwu.databaseservice.DatabaseFactory;
import com.example.cssnwu.po.PO;
import com.example.cssnwu.po.StudentPO;

import junit.framework.TestCase;

public class StudentDatabaseServiceTest extends TestCase {
	DatabaseFactory databaseFactory = null;
	StudentDatabaseService studentDatabaseService = null;
	
	
    @Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		databaseFactory = new DatabaseFactoryImpl();
		studentDatabaseService = (StudentDatabaseService) databaseFactory.getStudentDatabaseService();
	}
    
    public void testfind() throws RemoteException{
        int id = 1;
        StudentPO studentPO = (StudentPO) studentDatabaseService.find(id);
        assertEquals("谢福恒", studentPO.getUserName());
    }
    
    public void testfindAll() throws RemoteException {
    	ArrayList<PO> students = studentDatabaseService.findAll();
    	assertEquals(53, students.size());
    }
    
    public void testfindByKey() throws RemoteException {
    	String key = "地理学院";
    	ArrayList<PO> students = studentDatabaseService.find(key);
    	assertEquals(2, students.size());
    }
    
    public void testfindByKey2() throws RemoteException {
    	String key = "drop";
    	ArrayList<PO> students = studentDatabaseService.find(key);
    	assertEquals(1, students.size());
    }
    
    public void testfindByKey3() throws RemoteException {
    	String key = "drop";
    	ArrayList<PO> students = studentDatabaseService.find(key);
    	assertEquals(1, students.size());
    }
    
    
    
}
