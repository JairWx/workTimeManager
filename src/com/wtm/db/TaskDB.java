package com.wtm.db;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.wtm.domain.Task;

public class TaskDB extends BasicDB{
	
	public Task getOneTaskById(int id){
		Task task = null;
		try {
			 task = qr.query("SELECT * FROM task WHERE name=?", new BeanHandler<Task>(Task.class), id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;
	}
	
	public Task insertOneTask(String name,long start_time,String ymd){
		Task task = null;
		try {
			 task = qr.insert("INSERT INTO task(`name`,`start_time`,`ymd`) VALUES(?,?,?);", new BeanHandler<Task>(Task.class),
					 name,start_time,ymd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return task;
	}
	
	public int updateTaskById(long end_time,int id){
		int result = 0;
		try {
		 result = qr.update("UPDATE task set `end_time` = ? where id = ?", end_time,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		TaskDB db = new TaskDB();
		Task task = db.insertOneTask("ssss", 12312312, "20180606");
		System.out.println(task);
	}
}
