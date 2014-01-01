package com.example.cssnwu.junit;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.example.cssnwu.database.CourseDatabaseService;
import com.example.cssnwu.database.DatabaseFactoryImpl;
import com.example.cssnwu.database.TeacherDatabaseService;
import com.example.cssnwu.databaseservice.DatabaseFactory;
import com.example.cssnwu.po.PO;
import com.example.cssnwu.po.TeacherPO;

import junit.framework.TestCase;

public class TeacherDatabaseServiceTest extends TestCase {
	DatabaseFactory databaseFactory = null;
	TeacherDatabaseService teacherDatabaseService = null;
	
	
    @Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		databaseFactory = new DatabaseFactoryImpl();
		teacherDatabaseService = (TeacherDatabaseService) databaseFactory.getTeacherDatabaseService();
	}


	public void testfind() throws RemoteException{
         int teacherId = 333;
         TeacherPO teacherPO = (TeacherPO) teacherDatabaseService.find(teacherId);
         assertEquals("刘钦", teacherPO.getUserName());
    }
	
	public void testfindByKey() throws RemoteException{
        String key = "地理学院";
        ArrayList<PO> teachers =  teacherDatabaseService.find(key);
        System.out.println(teachers.size());
        assertEquals(1, teachers.size());
   }
	
	public void testfindAll() throws RemoteException {
		ArrayList<PO> teachers = teacherDatabaseService.findAll();
		assertEquals(22, teachers.size());
	}
	
}
