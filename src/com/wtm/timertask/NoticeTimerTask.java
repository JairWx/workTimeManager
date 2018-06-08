package com.wtm.timertask;

import java.awt.event.ComponentAdapter;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.wtm.db.TaskDB;
import com.wtm.domain.Task;
import com.wtm.frame.ClockFrame;
import com.wtm.frame.NoticeFrame;

public class NoticeTimerTask extends TimerTask {
	
	
	private int sethour;  
	private int setminute;  
	private int setsecond;
	
	private int settime = 60000;
	
	private Calendar setCalendar;
	
	private long timeRemaing = 0;
	
	private String taskerTitile = "";
    
	private TaskDB db;
	
	private int task_id;
	
	private ClockFrame clockFrame;
	
	public NoticeTimerTask(String title){
		//插入记录
		db = new TaskDB();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
		String ymd = sdf.format(new Date());
		Task task = db.insertOneTask(title, System.currentTimeMillis(), ymd);
		this.task_id = task.getId();
		this.taskerTitile = title;
	}
	
	public void setClockFrame (ClockFrame clockFrame){
		this.clockFrame = clockFrame;
	}
    public void setTimer(int sethour,int setminute,int setsecond){
    	this.sethour = sethour;
    	this.setminute = setminute;
    	this.setsecond = setsecond;
    }
    
    
	public Calendar getSetCalendar() {
		return setCalendar;
	}

	public void setSetCalendar(Calendar setCalendar) {
		this.setCalendar = setCalendar;
	}

	public String getTaskerTitile() {
		return taskerTitile;
	}



	public long getTimeRemaing() {
		return timeRemaing;
	}

	public void setTimeRemaing(long timeRemaing) {
		this.timeRemaing = timeRemaing;
	}

	public void setTaskerTitile(String taskerTitile) {
		this.taskerTitile = taskerTitile;
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		GregorianCalendar calendar = new GregorianCalendar();  
        int hour = calendar.get(Calendar.HOUR_OF_DAY);  
        int minute = calendar.get(Calendar.MINUTE);  
        int second = calendar.get(Calendar.SECOND);  
        //计算间隔时间
        if(setCalendar != null) {
        	 this.timeRemaing = setCalendar.getTimeInMillis() - calendar.getTimeInMillis();
        	 if(clockFrame != null) {
        		 clockFrame.updatetimeRemStr("还剩:"+ timeRemaing /(1000 * 60) +"m,"+timeRemaing%(1000 * 60) / 1000+"s");
        	 }
        }
        if(sethour == hour && setminute == minute && setsecond == second){  
        	this.db.updateTaskById(System.currentTimeMillis(), task_id);
            //playMusic(file);  
        	NoticeFrame n1 = new NoticeFrame();
        	n1.setContentPane(new JLabel(taskerTitile));
        	n1.setVisible(true);
            try {
				Thread.sleep(settime);
				//n1.setUndecorated(false);
				//SwingUtilities.updateComponentTreeUI(n1);
				n1.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						n1.setVisible(false);
					}
					
				});
				setCalendar = null;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //插入记录
            
          }  
	}

}
