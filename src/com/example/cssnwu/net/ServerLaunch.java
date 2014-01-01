/**
 * @(#)ServerLaunch.java     	2013-10-3 下午2:35:29
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
 *Class <code>ServerLaunch.java</code> 创建RMI注册表，启动RMI服务，并将远程对象注册到RMI注册表中。
 *
 * @author never
 * @version 2013-10-3
 * @since JDK1.7
 */
public class ServerLaunch {
	public static final String IP = "127.0.0.1";            //ip地址
	public static final int PORT = 9999;                    //端口号
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
            //创建一个远程对象
            getDatabaseFactory();
            //本地主机上的远程对象注册表Registry的实例，并指定端口为8888，这一步必不可少（Java默认端口是1099），必不可缺的一步，缺少注册表创建，则无法绑定对象到远程注册表上
            LocateRegistry.createRegistry(PORT);

            //把远程对象注册到RMI注册服务器上，并命名为IDataRemoteService
            //绑定的URL标准格式为：rmi://host:port/name
            Naming.bind("rmi://" + IP + ":" + PORT + "/cssnwu",databaseFactory);

            System.out.println(">>>>>INFO:远程对象绑定成功！");
        } catch (RemoteException e) {
            System.out.println("创建远程对象发生异常！");
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            System.out.println("发生重复绑定对象异常！");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("发生URL畸形异常！");
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
