package com.wtm.main;

import java.awt.SystemTray;
import java.awt.TrayIcon;

/**
 * 启动程序
 * @author wenjie
 *
 */
public class Main {
	private static SystemTray systemTray;
	private static TrayIcon trayIcon;
	
	public static void main(String[] args) {
		new Wtm();
		//addTray();
        //Clock t = new Clock();  
        //t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
       // t.setAlwaysOnTop(true);//总是在程序的前面
        //t.setVisible(true);  
        //t.setLocationRelativeTo(null);//窗体显示在屏幕中央  
		
    }
     
}
