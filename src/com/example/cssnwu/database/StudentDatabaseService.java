/**
 * @(#)StudentDatabaseService.java     	2013-10-10 ����9:18:00
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.database;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.cssnwu.businesslogicservice.resultenum.StudentType;
import com.example.cssnwu.databaseservice.DatabaseService;
import com.example.cssnwu.po.PO;

/**
 *Class <code>StudentDatabaseService.java</code> ��Student�����йص����ݲ�����
 *
 * @author never
 * @version 2013-10-10
 * @since JDK1.7
 */
@SuppressWarnings("serial")
public class StudentDatabaseService extends UnicastRemoteObject implements DatabaseService {

	/**���췽��
	 * @throws RemoteException
	 */
	protected StudentDatabaseService() throws RemoteException {
		super();
	}

	/* (non-Javadoc)
	 * Title: find
	 * Description:ͨ��ѧ�Ų�ѯStudentPO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(int)
	 */
	@Override
	public PO find(int id) throws RemoteException {
		// TODO �˴�������ݿ�����Ĵ��� 
		return null;
	}

	/* (non-Javadoc)
	 * Title: find
	 * Description:ͨ���ؼ��ֲ�ѯ��ͬ���͵�ѧ��
	 * @see com.example.cssnwu.databaseservice.DatabaseService#find(java.lang.String)
	 */
	@Override
	public ArrayList<PO> find(String key) throws RemoteException {
		// TODO �˴�������ݿ�����Ĵ��� 
		
		//���ֹؼ��ֽ��д���
		if(key.equals(StudentType.Drop)) {
			
		} else if(key.equals(StudentType.Graduate)) {
			
		} else if(key.equals(StudentType.StayDown)) {
			
		} else if(key.equals(StudentType.Transfer)) {
			
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * Title: findAll
	 * Description:��ѯ���е�ѧ��
	 * @see com.example.cssnwu.databaseservice.DatabaseService#findAll()
	 */
	@Override
	public ArrayList<PO> findAll() throws RemoteException {
		// TODO �˴�������ݿ�����Ĵ��� 
		return null;
	}

	/* (non-Javadoc)
	 * Title: findAll
	 * Description:ͨ���γ̱�Ų鿴ѡ��ÿγ̵�����StudentPO
	 * @see com.example.cssnwu.databaseservice.DatabaseService#findAll(int)
	 */
	@Override
	public ArrayList<PO> findAll(int id) throws RemoteException {
		//TODO  �˴�������ݿ�����Ĵ��� 
		return null;
	}

	/* (non-Javadoc)
	 * Title: update
	 * Description:����PO����
	 * @see com.example.cssnwu.databaseservice.DatabaseService#update(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean update(PO po) throws RemoteException {
		// TODO �˴�������ݿ�����Ĵ��� 
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
		// TODO �˴�������ݿ�����Ĵ��� 
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
	 * Description:����StudentPO����
	 * @see com.example.cssnwu.databaseservice.DatabaseService#insert(com.example.cssnwu.po.PO)
	 */
	@Override
	public boolean insert(PO po) throws RemoteException {
		// TODO �˴�������ݿ�����Ĵ��� 
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
