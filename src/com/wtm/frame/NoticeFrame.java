package com.wtm.frame;

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
		setSize(1000, 1000);
		this.setLocationRelativeTo(null);// 居中显示
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
	}
}
