package com.wtm.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

public class BasicUtils {
	
	static Dimension  screenSize = new Dimension();
	static {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //获取屏幕的尺寸   	 
	}
	
	public static int getScreenWidth(){
		return screenSize.width;
	}
	
	public static int getScreenHeight(){
		return screenSize.width;
	}
	
	/**
	 * 获取屏幕的中间位置
	 * @return
	 */
	public static Dimension getScreenCenterDimension(){
		return new Dimension(screenSize.width/2,screenSize.height/2);
	}
}
