package com.wtm.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.wtm.jpanel.CountDownPanel;

import javax.swing.JButton;

public class ClockFrame extends JFrame {
	
	private final int CLOCK_WIDTH = 120;
	private final int CLOCK_HEIGHT = 135;
	private final int DEFAULT_WIDTH = 140;
	private final int DEFAULT_HEIGHT = 200;
	private static final long serialVersionUID = 1L;
	public static final int X = 60;  
    public static final int Y = 60;  
    public static final int X_BEGIN = 10;  
    public static final int Y_BEGIN = 10;  
    public static final int RADIAN = 50;  
    Ellipse2D.Double e;  
    int x;  
    int y;  
    int hour;  
    int minute;  
    int second;  
    Line2D.Double hourLine;  
    Line2D.Double minLine;  
    Line2D.Double secondLine;  
    GregorianCalendar currentCalendar;//当前的时间
    String timestr = "";  
    //volatile  String  timeRemStr = "";
    CountDownPanel j = new CountDownPanel();
    //JLabel label;
	/**
	 * Create the frame.
	 */
	public ClockFrame(GregorianCalendar currentCalendar) {
		//外观优化
		Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包  
    	Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸   	  
    	int screenWidth = screenSize.width; //获取屏幕的宽    
    	int screenHeight = screenSize.height; //获取屏幕的高  
		
		this.setUndecorated(true);
        setOpacity(0.8f);
        
		this.currentCalendar = currentCalendar;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(200, 200, 450, 300);
		setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		//setSize(140, 180); 
		setLocation(screenWidth - getWidth(), 0);
		getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.add(new MyPanel());//在时钟pannel再套一层panel是为了居中显示
		getContentPane().add(panel, BorderLayout.NORTH);
		JPanel panel_1 = new JPanel();
		panel_1.add(j);
		getContentPane().add(panel_1, BorderLayout.CENTER);
		
		//j = new JPanel();
		JButton btnNewButton = new RButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		panel_1.add(btnNewButton);
		panel_1.add(j);
		
		Timer t = new Timer(); 
		Task task = new Task();  
        t.schedule(task, 0, 1000);//每秒刷新一次  
	}
	
	class MyPanel extends JPanel {  
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyPanel() {
	        e = new Ellipse2D.Double(X_BEGIN, Y_BEGIN, 100, 100);  
	        hourLine = new Line2D.Double(X, Y, X, Y);  
	        minLine = new Line2D.Double(X, Y, X, Y);  
	        secondLine = new Line2D.Double(X, Y, X, Y);
	        setPreferredSize(new Dimension(CLOCK_WIDTH, CLOCK_HEIGHT)); //设置大小
	        setAlignmentX(CENTER_ALIGNMENT);
	    }  
	  
	    public void paintComponent(Graphics g) { 
	        super.paintComponent(g);  
	        Graphics2D g2 = (Graphics2D) g;  
	        g2.drawString("12", 55, 25);//整点时间  
	        g2.drawString("6", 55, 105);  
	        g2.drawString("9", 15, 65);  
	        g2.drawString("3", 100, 65);  
	        g2.drawString(timestr, 0, 130);  
	        //g2.drawString(timeRemStr, 0, 150); 
	        g2.draw(e);  
	        g2.draw(hourLine);//时针  
	        g2.draw(minLine);//分针  
	        g2.draw(secondLine);//秒针  
	    }  
	}
	class Task extends TimerTask {
	    public void run() {  
	    	currentCalendar = new GregorianCalendar();  
	        hour = currentCalendar.get(Calendar.HOUR_OF_DAY);  
	        minute = currentCalendar.get(Calendar.MINUTE);  
	        second = currentCalendar.get(Calendar.SECOND);  
	         String m = String.valueOf(minute);
	        if(minute < 10){
	        	m = "0"+m;
	        }
	        String s = String.valueOf(second);
	        if(second < 10){
	        	s = "0"+s;
	        }
	        timestr = "当前时间：" + hour + " : " + m + " : " + s;  
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
	
	public void updatetimeRemStr(String text){
		j.update(text);
    	setSize(DEFAULT_WIDTH, DEFAULT_WIDTH+j.getHeight());
	}

}

