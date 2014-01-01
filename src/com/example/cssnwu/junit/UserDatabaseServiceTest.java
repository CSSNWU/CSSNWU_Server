package com.example.cssnwu.junit;

import java.rmi.RemoteException;

import com.example.cssnwu.businesslogicservice.resultenum.UserType;
import com.example.cssnwu.database.DatabaseFactoryImpl;
import com.example.cssnwu.database.UserDatabaseService;
import com.example.cssnwu.databaseservice.DatabaseFactory;
import com.example.cssnwu.po.PO;
import com.example.cssnwu.po.TeacherPO;
import com.example.cssnwu.po.UserPO;

import junit.framework.TestCase;

public class UserDatabaseServiceTest extends TestCase{
	DatabaseFactory databaseFactory = null;
	UserDatabaseService userDatabaseService = null;
	
	
    @Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		databaseFactory = new DatabaseFactoryImpl();
		userDatabaseService = (UserDatabaseService) databaseFactory.getUserDatabaseService();
	}


    public void testfindStudent() throws RemoteException{
    	int id = 1;
    	UserPO user = (UserPO) userDatabaseService.find(1);
    	assertEquals("1", user.getPassword());
    }
    
    public void testfindStudent2() throws RemoteException{
    	int id = 111160126;
    	UserPO student2 = (UserPO) userDatabaseService.find(id);
    	assertEquals(UserType.Student, student2.getUserType());
    }
    
    public void testfindTeacher() throws RemoteException{

    	int teacherid = 333;
    	TeacherPO teacher = (TeacherPO) userDatabaseService.find(teacherid);
    	assertEquals("¡ı«’", teacher.getUserName());
    }
    
    
    public void testUpdate() throws RemoteException {
    	UserPO user = (UserPO) userDatabaseService.find(1);
    	String attr = "name";
    	String name = "–ª∏£∫„";
    	
    	boolean  result = userDatabaseService.update(user,attr,name);
    	assertEquals(true, result);
    }
    
}
