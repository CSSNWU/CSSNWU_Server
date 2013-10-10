/**
 * @(#)CourseDatabaseService.java     	2013-10-10 ����8:37:25
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
 *Class <code>CourseDatabaseService.java</code> ��Course������ص����ݴ�����
 *
 * @author never
 * @version 2013-10-10
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class CourseDatabaseService extends UnicastRemoteObject implements DatabaseService{

	/**���췽��
	 * @throws RemoteException
	 */
	protected CourseDatabaseService() throws RemoteException {
		super();
	}

	/* (non-Javadoc)
	 * Title: find
	 * Description: ͨ��id��ѯCoursePO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(int)
	 */
	@Override
	public PO find(int id) throws RemoteException {
		// TODO �˴��������ݿ�����Ĵ��� 
		return null;
	}

	/* (non-Javadoc)
	 * Title: find
	 * Description:ͨ���ؼ��ֲ�ѯCoursePO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(java.lang.String)
	 */
	@Override
	public ArrayList<PO> find(String key) throws RemoteException {
		// TODO �˴��������ݿ�����Ĵ���   key������course���������ԣ�����Ҫ�������е��������鿴�Ƿ�ƥ��
		return null;
	}

	/* (non-Javadoc)
	 * Title: findAll
	 * Description:��ѯ���е�CoursePO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#findAll()
	 */
	@Override
	public ArrayList<PO> findAll() throws RemoteException {
		// TODO �˴��������ݿ�����Ĵ��� 
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
	 * Description:����PO����
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean update(PO po) throws RemoteException {
		// TODO �˴��������ݿ�����Ĵ��� 
		return false;
	}

	/* (non-Javadoc)
	 * Title: update
	 * Description:����PO�����ĳ������ֵ
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean update(PO po, String attr, Object value)
			throws RemoteException {
		// TODO �˴��������ݿ�����Ĵ��� 
		return false;
	}

	/* (non-Javadoc)
	 * Title: update
	 * Description:����id��ѯ���γ̶���Ȼ��Ǽ�ѧ���ɼ�
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(int, java.util.HashMap)
	 */
	@Override
	public boolean update(int id, HashMap<Integer, Double> map)
			throws RemoteException {
		// TODO �˴��������ݿ�����Ĵ��� 
		return false;
	}

	/* (non-Javadoc)
	 * Title: insert
	 * Description:����CoursePO����
	 * @see com.example.cssnwu.databaseservice.DatabaseService#insert(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean insert(PO po) throws RemoteException {
		// TODO �˴��������ݿ�����Ĵ��� 
		return false;
	}

	/* (non-Javadoc)
	 * Title: delete
	 * Description:ɾ��PO����
	 * @see com.example.cssnwu.databaseservice.DatabaseService#delete(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean delete(PO po) throws RemoteException {
		return false;
	}

	/* (non-Javadoc)
	 * Title: load
	 * Description:��������
	 * @see com.example.cssnwu.databaseservice.DatabaseService#load()
	 */
	@Override
	public void load() throws RemoteException {
		
	}

	/* (non-Javadoc)
	 * Title: save
	 * Description:��������
	 * @see com.example.cssnwu.databaseservice.DatabaseService#save()
	 */
	@Override
	public void save() throws RemoteException {
		
	}

	/* (non-Javadoc)
	 * Title: init
	 * Description:��ʼ������
	 * @see com.example.cssnwu.databaseservice.DatabaseService#init()
	 */
	@Override
	public void init() throws RemoteException {
		
	}

	/* (non-Javadoc)
	 * Title: finish
	 * Description:���������ݵĲ���
	 * @see com.example.cssnwu.databaseservice.DatabaseService#finish()
	 */
	@Override
	public void finish() throws RemoteException {
	}

}