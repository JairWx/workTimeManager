package com.wtm.frame;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class NoticeFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoticeFrame() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		// setLayout(null);
		//SwingUtilities.updateComponentTreeUI(this);
		Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包  
    	Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸   	  
    	int screenWidth = screenSize.width; //获取屏幕的宽    
    	int screenHeight = screenSize.height; //获取屏幕的高  
		setSize(screenWidth, screenHeight);
		this.setUndecorated(true);
		setOpacity(0.5f);
		this.setLocationRelativeTo(null);// 居中显示
		//this.setUndecorated(true);
		this.setAlwaysOnTop(true);
	}
}
