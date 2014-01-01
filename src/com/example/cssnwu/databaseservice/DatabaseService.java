//	to my dearest old wang,
//	without whom 
//	i could NEVER have accomplished all these piles of work. 

/**
 * @(#)DatabaseService.java     	2013-10-5 上午9:54:41
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.databaseservice;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.cssnwu.businesslogicservice.resultenum.SYSTEM_STATE;
import com.example.cssnwu.po.PO;

/**
 *Class <code>DatabaseService.java</code> 数据操作接口
 *
 * @author never
 * @version 2013-10-5
 * @since JDK1.7
 */
public interface DatabaseService extends Remote,Serializable{
	/**
	 * Title: find
	 * Description:通过id查询PO对象
	 * @param id   ID
	 * @return     PO
	 * @throws RemoteException
	 */
    public PO find(int id) throws RemoteException;
    
    /**
     * Title: find
     * Description:通过关键字查询PO对象
     * @param key  关键字（String）
     * @return  PO对象的列表（ArrayList）
     * @throws RemoteException
     */
    public ArrayList<PO> find(String key) throws RemoteException;
    
    /**
     * Title: findAll
     * Description:查询所有PO对象
     * @return  PO对象的列表（ArrayList）
     * @throws RemoteException
     */
    public ArrayList<PO> findAll() throws RemoteException;
    
    /**
     * Title: findAll
     * Description:通过id获取某一类的所有PO对象
     * @param id   ID
     * @return    PO对象的列表（ArrayList）
     * @throws RemoteException
     */
    public ArrayList<PO> findAll(int id) throws RemoteException;
    
    /**
     * Title: update
     * Description:更新PO对象
     * @param po   PO
     * @return     更新操作的结果
     * @throws RemoteException
     */
    public boolean update(PO po) throws RemoteException;
    
    /**
     * Title: update
     * Description:更新PO对象的某个属性值
     * @param po       PO对象
     * @param attr     属性名
     * @param value    属性值
     * @return         更新操作的结果
     * @throws RemoteException
     */
    public boolean update(PO po,String attr,Object value) throws RemoteException;
    
    /**
     * Title: update
     * Description:根据id查询到对象，然后更新一组数据
     * @param id    ID
     * @param map   HashMap
     * @return      更新操作的结果
     * @throws RemoteException
     */
    public boolean update(int id,HashMap<Integer, Double> map) throws RemoteException;
    
    /**
     * Title: insert
     * Description: 插入PO对象
     * @param po    PO
     * @return      插入操作的结果
     * @throws RemoteException
     */
    public boolean insert(PO po) throws RemoteException;
    
    /**
     * Title: delete
     * Description:  删除PO对象
     * @param po     PO
     * @return       删除操作的结果
     * @throws RemoteException
     */
    public boolean delete(PO po) throws RemoteException;
    
    /**
     * Title: load
     * Description:加载数据
     * @throws RemoteException
     */
    public void load() throws RemoteException;
    
    /**
     * Title: save
     * Description:保存数据
     * @throws RemoteException
     */
    public void save() throws RemoteException;
    
    /**
     * Title: init
     * Description:初始化数据
     * @throws RemoteException
     */
    public void init() throws RemoteException;
    
    /**
     * Title: finish
     * Description:结束对数据的操作
     * @throws RemoteException
     */
    public void finish() throws RemoteException;
    
    /**
     * Title: checkSystemState
     * Description:检查系统状态，如果数据库中对应状态为0则用户不能进行操作，如果数据库中对应状态为1则用户可以操作
     * @throws RemoteException
     */
    public boolean checkSystemState(SYSTEM_STATE system_state) throws RemoteException;
    
    public boolean setSystemState(SYSTEM_STATE systemState,
    		boolean wantFunctionUsable) throws RemoteException;
    
    
}
