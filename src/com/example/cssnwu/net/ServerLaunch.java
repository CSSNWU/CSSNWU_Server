/**
 * @(#)ServerLaunch.java     	2013-10-3 ����2:35:29
 * Copyright never.All rights reserved
 * never PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.example.cssnwu.net;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import com.example.cssnwu.database.DatabaseFactoryImpl;
import com.example.cssnwu.databaseservice.DatabaseFactory;


/**
 *Class <code>ServerLaunch.java</code> ����RMIע�������RMI���񣬲���Զ�̶���ע�ᵽRMIע����С�
 *
 * @author never
 * @version 2013-10-3
 * @since JDK1.7
 */
public class ServerLaunch {
	public static final String IP = "127.0.0.1";            //ip��ַ
	public static final int PORT = 9999;                    //�˿ں�
	private static DatabaseFactory databaseFactory = null;
	
	
//	public static void main(String args[]) {
//		 try {
//			InetAddress inetAddress = InetAddress.getLocalHost();
//			System.out.println("IP:" + inetAddress);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//         new ServerLaunch().createRemote();
//
//    } 
	
	public void createRemote(){
        try {
            //����һ��Զ�̶���
            getDatabaseFactory();
            //���������ϵ�Զ�̶���ע���Registry��ʵ������ָ���˿�Ϊ8888����һ���ز����٣�JavaĬ�϶˿���1099�����ز���ȱ��һ����ȱ��ע����������޷��󶨶���Զ��ע�����
            LocateRegistry.createRegistry(PORT);

            //��Զ�̶���ע�ᵽRMIע��������ϣ�������ΪIDataRemoteService
            //�󶨵�URL��׼��ʽΪ��rmi://host:port/name
            Naming.bind("rmi://" + IP + ":" + PORT + "/cssnwu",databaseFactory);

            System.out.println(">>>>>INFO:Զ�̶���󶨳ɹ���");
        } catch (RemoteException e) {
            System.out.println("����Զ�̶������쳣��");
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            System.out.println("�����ظ��󶨶����쳣��");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("����URL�����쳣��");
            e.printStackTrace();
        }
	}
	
	public static DatabaseFactory getDatabaseFactory(){
		if(databaseFactory == null){
			try {
				databaseFactory = new DatabaseFactoryImpl();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
		return databaseFactory;
	}
	
	public void close()  {
		
		if(databaseFactory != null) {
			databaseFactory=null;
			
		}
		try {
			Naming.unbind("rmi://" + IP + ":" + PORT + "/cssnwu");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		
	}
}
