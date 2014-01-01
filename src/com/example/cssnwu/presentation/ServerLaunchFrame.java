package com.example.cssnwu.presentation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import com.example.cssnwu.net.ServerLaunch;

public class ServerLaunchFrame extends JFrame{
    private JButton start_button,stop_button;
    private JTextArea out_textArea;
    private JLabel infoLabel = new JLabel("IP: ");
    
    private static ServerLaunch serverLaunch = null;
    
    public ServerLaunchFrame() {
    	serverLaunch = new ServerLaunch();
    	initView();
    }
    
    private void initView() {
    	setSize(new Dimension(300, 200));
    	setLayout(null);
    	
    	
    	start_button = new JButton("开启服务器");
    	start_button.setBounds(50, 50,100,30);
    	getContentPane().add(start_button);
    	
    	infoLabel.setBounds(5, 5, 200, 10);
    	infoLabel.setVisible(true);
    	getContentPane().add(infoLabel);
    	out_textArea = new JTextArea();
    	getContentPane().add(out_textArea);
    	out_textArea.append("rrr");
    	
    	start_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				serverLaunch.createRemote();
				try {
					InetAddress inetAddress = InetAddress.getLocalHost();
					infoLabel.setText("IP: " + inetAddress);
				} catch (UnknownHostException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
			}
		});
    	
     	stop_button = new JButton("关闭服务器");
     	stop_button.setBounds(50, 100, 100, 30);
    	getContentPane().add(stop_button);
    	
    	stop_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				serverLaunch.close();
				ServerLaunchFrame.this.dispose();
			
			}
		});
    	
    	setVisible(true);
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ServerLaunchFrame frame=new ServerLaunchFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
         
	}

}
