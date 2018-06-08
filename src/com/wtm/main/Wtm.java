package com.wtm.main;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JOptionPane;

import com.wtm.frame.AboutFrame;
import com.wtm.frame.ClockFrame;
import com.wtm.frame.TaskFrame;

/**
 * 时间管理器主程序
 * @author wenjie
 *
 */
public class Wtm {
	private ClockFrame clockFrame;
	private  SystemTray systemTray;
	private  TrayIcon trayIcon;
	
	private GregorianCalendar currentCalendar;//当前的时间
	
	public Wtm(){
		//显示时钟
		clockFrame = new ClockFrame(currentCalendar);
		clockFrame.setVisible(true);
		clockFrame.setAlwaysOnTop(true);
		addTray();
	}
	private  void addTray(){
    	String iconpath="ipsicon.png";//任务栏图标，最小化窗口  
    	 if (SystemTray.isSupported()) {// 判断系统是否支持系统托盘  
             if (systemTray==null) {  
                 systemTray=SystemTray.getSystemTray();//创建系统托盘  
                 if (trayIcon!=null) {  
                     systemTray.remove(trayIcon);  
                 }  
             }  
             //创建弹出式菜单  
             PopupMenu popup=new PopupMenu();  
             //主界面选项  
             MenuItem mainMenuItem=new MenuItem("主界面");  
//           mainMenuItem.setActionCommand("main menu");  
             mainMenuItem.addActionListener(new ActionListener(){  
                 @Override  
                 public void actionPerformed(ActionEvent e) {  
                     //setVisible(true);  
//                   setAlwaysOnTop(true);  
//                   systemTray.remove(trayIcon);  
                 }  
             });
             
             //主界面选项  
             MenuItem aboutMenuItem=new MenuItem("关于");  
//           mainMenuItem.setActionCommand("main menu");  
             aboutMenuItem.addActionListener(new ActionListener(){  
                 @Override  
                 public void actionPerformed(ActionEvent e) {  
                	 new AboutFrame().setVisible(true);
                 }  
             });
             MenuItem setters=new MenuItem("时间设置");  
//           mainMenuItem.setActionCommand("main menu");  
             setters.addActionListener(new ActionListener(){  
                 @Override  
                 public void actionPerformed(ActionEvent e) {  
                	 //sethour = Integer.parseInt(JOptionPane.showInputDialog("请输入小时："));  
                     //setminute = Integer.parseInt(JOptionPane.showInputDialog("请输入分钟："));  
                     //setsecond = Integer.parseInt(JOptionPane.showInputDialog("请输入秒："));  
                 }  
             });
             
             MenuItem setters2=new MenuItem("定时25分钟");  
//           mainMenuItem.setActionCommand("main menu");  
             setters2.addActionListener(new ActionListener(){  
                 @Override  
                 public void actionPerformed(ActionEvent e) {
                	
                    // Map<String,Object> dataPipeline = new ConcurrentHashMap<String,Object>();//普通的hashMap不行
                     TaskFrame frame = new TaskFrame(clockFrame);
                     frame.setVisible(true);
                  
                 }  
             });
             //退出程序选项  
             MenuItem exitMenuItem=new MenuItem("退出");  
//           exitMenuItem.setActionCommand("exit");  
             exitMenuItem.addActionListener(new ActionListener(){  
                 @Override  
                 public void actionPerformed(ActionEvent e) {  
//                   dispose();  
                     System.exit(0);  
                 }  
             });  
             popup.add(aboutMenuItem);
             popup.add(setters2);  
             //popup.add(setters);  
             popup.add(mainMenuItem);  
             //弹出式菜单添加分割线  
             popup.addSeparator();  
             popup.add(exitMenuItem);  
             Image iconImage=Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(iconpath));  
             trayIcon=new TrayIcon(iconImage, "Clock", popup);//创建trayIcon  
             trayIcon.setImageAutoSize(true);  
             trayIcon.addActionListener(new ActionListener() {  
                   
                 @Override  
                 public void actionPerformed(ActionEvent e) {  
                     // TODO Auto-generated method stub  
                     //setVisible(true);  
//                   setAlwaysOnTop(true);  
//                   systemTray.remove(trayIcon);  
                 }  
             });  
             try {  
                 systemTray.add(trayIcon);  
             } catch (AWTException e1) {  
                 // TODO Auto-generated catch block  
                 e1.printStackTrace();  
             }  
         }  
     } 
}
