/**
 * @(#)UserDatabaseService.java     	2013-10-8 下午3:00:14
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.database;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.cssnwu.databaseservice.DatabaseService;
import com.example.cssnwu.po.PO;

/**
 *Class <code>UserDatabaseService.java</code> TODO
 *
 * @author never
 * @version 2013-10-8
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class UserDatabaseService extends UnicastRemoteObject implements DatabaseService{
    
	
	
	/**构造方法
	 * @throws RemoteException
	 */
	protected UserDatabaseService() throws RemoteException {
		super();
	}

	/* (non-Javadoc)
	 * Title: find
	 * Description:通过id查询UserPO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(int)
	 */
	@Override
	public PO find(int id) throws RemoteException {
		// TODO 此处添加数据库操作的代码 
		return null;
	}

	/* (non-Javadoc)
	 * Title: find
	 * Description:通过关键字查询UserPO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(java.lang.String)
	 */
	@Override
	public ArrayList<PO> find(String key) throws RemoteException {
		// TODO 此处添加数据库操作的代码   key可以是院系或是其他
		return null;
	}

	/* (non-Javadoc)
	 * Title: findAll
	 * Description:查询所有UserPO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#findAll()
	 */
	@Override
	public ArrayList<PO> findAll() throws RemoteException {
		// TODO 此处添加数据库操作的代码 
		return null;
	}

	/* (non-Javadoc)
	 * Title: findAll
	 * Description:
	 * @see com.example.cssnwu.databaseservice.DatabaseService#findAll(int)
	 */
	@Override
	public ArrayList<PO> findAll(int id) throws RemoteException {
		return null;
	}

	/* (non-Javadoc)
	 * Title: update
	 * Description:更新PO对象
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean update(PO po) throws RemoteException {
		// TODO 此处添加数据库操作的代码 
		return false;
	}

	/* (non-Javadoc)
	 * Title: update
	 * Description:更新PO对象的某个属性值
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean update(PO po, String attr, Object value)
			throws RemoteException {
		// TODO 此处添加数据库操作的代码 
		return false;
	}

	/* (non-Javadoc)
	 * Title: update
	 * Description:
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(int, java.util.HashMap)
	 */
	@Override
	public boolean update(int id, HashMap<Integer, Double> map)
			throws RemoteException {
		return false;
	}

	/* (non-Javadoc)
	 * Title: insert
	 * Description:插入UserPO对象
	 * @see com.example.cssnwu.databaseservice.DatabaseService#insert(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean insert(PO po) throws RemoteException {
		// TODO 此处添加数据库操作的代码 
		return false;
	}

	/* (non-Javadoc)
	 * Title: delete
	 * Description:删除PO对象
	 * @see com.example.cssnwu.databaseservice.DatabaseService#delete(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean delete(PO po) throws RemoteException {
		return false;
	}

	/* (non-Javadoc)
	 * Title: load
	 * Description:加载数据
	 * @see com.example.cssnwu.databaseservice.DatabaseService#load()
	 */
	@Override
	public void load() throws RemoteException {
		
	}

	/* (non-Javadoc)
	 * Title: save
	 * Description:保存数据
	 * @see com.example.cssnwu.databaseservice.DatabaseService#save()
	 */
	@Override
	public void save() throws RemoteException {
		
	}

	/* (non-Javadoc)
	 * Title: init
	 * Description:初始化数据
	 * @see com.example.cssnwu.databaseservice.DatabaseService#init()
	 */
	@Override
	public void init() throws RemoteException {
		
	}

	/* (non-Javadoc)
	 * Title: finish
	 * Description:结束对数据的操作
	 * @see com.example.cssnwu.databaseservice.DatabaseService#finish()
	 */
	@Override
	public void finish() throws RemoteException {
	}



}
