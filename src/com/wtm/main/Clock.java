package com.wtm.main;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics;  
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;  
import java.awt.geom.Line2D;  
import java.util.Calendar;
import java.util.GregorianCalendar;  
import java.util.Timer;  
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;  
import javax.swing.JPanel;

import com.wtm.frame.AboutFrame;
import com.wtm.task.NoticeTask;  
  
//import javazoom.jl.player.Player;  
  
  
public class Clock extends JFrame {  
  
    MyPanel clockPanel;  
    Ellipse2D.Double e;  
    int x;  
    int y;  
    Line2D.Double hourLine;  
    Line2D.Double minLine;  
    Line2D.Double secondLine;  
    GregorianCalendar calendar;  
      
    int hour;  
    int minute;  
    int second;  
    String timestr = "";  
    String timeRemStr = "";
    static int sethour;  
    static int setminute;  
    static int setsecond;     
      
    public static final int X = 60;  
    public static final int Y = 60;  
    public static final int X_BEGIN = 10;  
    public static final int Y_BEGIN = 10;  
    public static final int RADIAN = 50;  
     
    public static int tasker_set_time = 25;
    private SystemTray systemTray;
    private TrayIcon trayIcon;
    
    //定时提醒
    private NoticeTask task2; 
    
    
    public Clock(){
    	
    	Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包  
    	  
    	Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸  
    	  
    	int screenWidth = screenSize.width; //获取屏幕的宽  
    	  
    	int screenHeight = screenSize.height; //获取屏幕的高  
    	
        setSize(140, 160); 
        this.setUndecorated(true);
        setOpacity(0.8f);
        setLocation(screenWidth - 130, 0);
        //this.setBackground(new Color(0,0,0,0));
        this.setAlwaysOnTop(true);
        clockPanel = new MyPanel();  
        add(clockPanel);  
        Timer t = new Timer();  
        Task task = new Task();  
        t.schedule(task, 0, 1000);//每秒刷新一次  
        task2 = new NoticeTask("提醒完成");  
        t.schedule(task2, 0, 1000);//每秒刷新一次  
        sts();
    }
     
    /**
     * 系统托盘
     */
    private void sts(){
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
                     setVisible(true);  
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
                	 sethour = Integer.parseInt(JOptionPane.showInputDialog("请输入小时："));  
                     setminute = Integer.parseInt(JOptionPane.showInputDialog("请输入分钟："));  
                     setsecond = Integer.parseInt(JOptionPane.showInputDialog("请输入秒："));  
                 }  
             });
             
             MenuItem setters2=new MenuItem("定时25分钟");  
//           mainMenuItem.setActionCommand("main menu");  
             setters2.addActionListener(new ActionListener(){  
                 @Override  
                 public void actionPerformed(ActionEvent e) {
                	 Calendar now = Calendar.getInstance(); 
                	 now.add(Calendar.MINUTE, Clock.tasker_set_time);
                	 sethour = now.get(Calendar.HOUR_OF_DAY);
                     setminute = now.get(Calendar.MINUTE);
                     setsecond = now.get(Calendar.SECOND); 
                     task2.setSetCalendar(now);
                     task2.setTimer(sethour, setminute, setsecond);
                     System.out.println(new StringBuffer(sethour).append(sethour).append(":").append(setminute).append(":").append(setsecond));
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
                     setVisible(true);  
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
   
    
      
//    public static void playMusic(File file) {  //显示mp3文件的绝对路径  
//       try {    
//          javax.media.Player player = null;    
//           if (file.exists()) {    
//    MediaLocator locator = new MediaLocator("file:"    
//                               + file.getAbsolutePath());    
//    System.out.println(file.getAbsolutePath());    
//           player = Manager.createRealizedPlayer(locator);    
//                 player.prefetch();// Ԥ准备读取    
//            player.start();// 开始读取    
//               } else {    
//                  System.out.println("没找到文件");    
//                }    
//              } catch (CannotRealizeException ex) {    
//                   ex.printStackTrace();    
//              } catch (NoPlayerException ex) {    
//                    ex.printStackTrace();    
//              } catch (IOException ex) {    
//                ex.printStackTrace();    
//               }    
//           }   
  
//    public void play() {//播放mp3文件  
//    	JFrame f =  new JFrame(){
//    		
//    	};
//    	f.getContentPane().add(new JLabel("时间到"));
//       f.setVisible(true);
//       f.setAlwaysOnTop(true);
//       f.setResizable(false);  
//       f.setSize(500, 500);
//       f.setLocationRelativeTo(null);
//       f.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//      // f.setUndecorated(true);
//       f.addWindowListener(new WindowAdapter() {  
//           @Override  
//           public void windowIconified(WindowEvent e) {  
//               setExtendedState(JFrame.NORMAL);  
//           }  
//         });
//    }  
  
      
    
    
  
class MyPanel extends JPanel {  
    public MyPanel() {
        e = new Ellipse2D.Double(X_BEGIN, Y_BEGIN, 100, 100);  
        hourLine = new Line2D.Double(X, Y, X, Y);  
        minLine = new Line2D.Double(X, Y, X, Y);  
        secondLine = new Line2D.Double(X, Y, X, Y);  
    }  
  
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);  
        Graphics2D g2 = (Graphics2D) g;  
        g2.drawString("12", 55, 25);//整点时间  
        g2.drawString("6", 55, 105);  
        g2.drawString("9", 15, 65);  
        g2.drawString("3", 100, 65);  
        g2.drawString(timestr, 0, 130);  
        g2.drawString(timeRemStr, 0, 150); 
        g2.draw(e);  
        g2.draw(hourLine);//时针  
        g2.draw(minLine);//分针  
        g2.draw(secondLine);//秒针  
    }  
}  
  
class Task extends TimerTask {
    public void run() {  
        calendar = new GregorianCalendar();  
        hour = calendar.get(Calendar.HOUR_OF_DAY);  
        minute = calendar.get(Calendar.MINUTE);  
        second = calendar.get(Calendar.SECOND);  
         String m = String.valueOf(minute);
        if(minute < 10){
        	m = "0"+m;
        }
        String s = String.valueOf(second);
        if(second < 10){
        	s = "0"+s;
        }
        timestr = "当前时间：" + hour + " : " + m + " : " + s;  
        timeRemStr = "还剩:"+ task2.getTimeRemaing() /(1000 * 60) +"m,"+task2.getTimeRemaing() %(1000 * 60) / 1000+"s";
        
        hourLine.x2 = X + 40 * Math.cos(hour * (Math.PI / 6) - Math.PI / 2);  
        hourLine.y2 = Y + 40 * Math.sin(hour * (Math.PI / 6) - Math.PI / 2);  
        minLine.x2 = X + 45  
                * Math.cos(minute * (Math.PI / 30) - Math.PI / 2);  
        minLine.y2 = Y + 45  
                * Math.sin(minute * (Math.PI / 30) - Math.PI / 2);  
        secondLine.x2 = X + 50  
                * Math.cos(second * (Math.PI / 30) - Math.PI / 2);  
        secondLine.y2 = Y + 50  
                * Math.sin(second * (Math.PI / 30) - Math.PI / 2);  
        repaint();  
     }  
   }  
}