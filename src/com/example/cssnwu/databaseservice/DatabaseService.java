/**
 * @(#)DatabaseService.java     	2013-10-5 ����9:54:41
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.databaseservice;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.cssnwu.po.PO;

/**
 *Class <code>DatabaseService.java</code> ���ݲ����ӿ�
 *
 * @author never
 * @version 2013-10-5
 * @since JDK1.7
 */
public interface DatabaseService extends Remote,Serializable{
	/**
	 * Title: find
	 * Description:ͨ��id��ѯPO����
	 * @param id   ID
	 * @return     PO
	 * @throws RemoteException
	 */
    public PO find(int id) throws RemoteException;
    
    /**
     * Title: find
     * Description:ͨ���ؼ��ֲ�ѯPO����
     * @param key  �ؼ��֣�String��
     * @return  PO������б�ArrayList��
     * @throws RemoteException
     */
    public ArrayList<PO> find(String key) throws RemoteException;
    
    /**
     * Title: findAll
     * Description:��ѯ����PO����
     * @return  PO������б�ArrayList��
     * @throws RemoteException
     */
    public ArrayList<PO> findAll() throws RemoteException;
    
    /**
     * Title: findAll
     * Description:ͨ��id��ȡĳһ�������PO����
     * @param id   ID
     * @return    PO������б�ArrayList��
     * @throws RemoteException
     */
    public ArrayList<PO> findAll(int id) throws RemoteException;
    
    /**
     * Title: update
     * Description:����PO����
     * @param po   PO
     * @return     ���²����Ľ��
     * @throws RemoteException
     */
    public boolean update(PO po) throws RemoteException;
    
    /**
     * Title: update
     * Description:����PO�����ĳ������ֵ
     * @param po       PO����
     * @param attr     ������
     * @param value    ����ֵ
     * @return         ���²����Ľ��
     * @throws RemoteException
     */
    public boolean update(PO po,String attr,Object value) throws RemoteException;
    
    /**
     * Title: update
     * Description:����id��ѯ������Ȼ�����һ������
     * @param id    ID
     * @param map   HashMap
     * @return      ���²����Ľ��
     * @throws RemoteException
     */
    public boolean update(int id,HashMap<Integer, Double> map) throws RemoteException;
    
    /**
     * Title: insert
     * Description: ����PO����
     * @param po    PO
     * @return      ��������Ľ��
     * @throws RemoteException
     */
    public boolean insert(PO po) throws RemoteException;
    
    /**
     * Title: delete
     * Description:  ɾ��PO����
     * @param po     PO
     * @return       ɾ�������Ľ��
     * @throws RemoteException
     */
    public boolean delete(PO po) throws RemoteException;
    
    /**
     * Title: load
     * Description:��������
     * @throws RemoteException
     */
    public void load() throws RemoteException;
    
    /**
     * Title: save
     * Description:��������
     * @throws RemoteException
     */
    public void save() throws RemoteException;
    
    /**
     * Title: init
     * Description:��ʼ������
     * @throws RemoteException
     */
    public void init() throws RemoteException;
    
    /**
     * Title: finish
     * Description:���������ݵĲ���
     * @throws RemoteException
     */
    public void finish() throws RemoteException;
}
