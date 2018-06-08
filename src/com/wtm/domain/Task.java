package com.wtm.domain;

public class Task {
	private int id;
	private String name;
	private int startTime;
	private int endTime;
	private String ymd;
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	
}
