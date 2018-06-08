package com.wtm.task;

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
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.wtm.db.TaskDB;
import com.wtm.domain.Task;
import com.wtm.frame.ClockFrame;
import com.wtm.frame.NoticeFrame;
import com.wtm.timertask.NoticeTimerTask;

public class NoticeTask{
	
	public NoticeTask(String title,ClockFrame  f){
		//插入记录
		Calendar now = Calendar.getInstance(); 
   	 now.add(Calendar.MINUTE, 25);
   	 int sethour = now.get(Calendar.HOUR_OF_DAY);
        int setminute = now.get(Calendar.MINUTE);
        int setsecond = now.get(Calendar.SECOND); 
        NoticeTimerTask task2 = new NoticeTimerTask(title); 
        task2.setClockFrame(f);
        task2.setSetCalendar(now);
        task2.setTimer(sethour, setminute, setsecond);
        Timer t = new Timer();  
        t.schedule(task2, 0, 1000);//每秒刷新一次  
        System.out.println(new StringBuffer(sethour).append(sethour).append(":").append(setminute).append(":").append(setsecond));
	}

}
