package com.wtm.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.wtm.utils.BasicUtils;

/**
 * 基本样式
 * @author wenjie
 *
 */
public class BasicFrame  extends JFrame{
	
	private Dimension defaultLocation = BasicUtils.getScreenCenterDimension();
	
	private JButton submitBtn = new JButton("确定"); 
	
	private JButton cancleBtn = new JButton("取消");
	
	private int status = 0;
	
	public BasicFrame(){
		//contentPane = new JPanel();
		setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
		//add(contentPane, BorderLayout.CENTER);
		setLocation(defaultLocation.width-getWidth()/2, defaultLocation.height - getHeight()/2);
		//this.setUndecorated(true);
		//setOpacity(0.8f);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//默认按钮
		JPanel btnJPanel = new JPanel();
		btnJPanel.add(submitBtn);
		//JButton jb = new JButton("取消");
		cancleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				status = 2;
				setVisible(false);
			}
		});
		setSubmitBtnAction(null);
		btnJPanel.add(cancleBtn);
		add(btnJPanel, BorderLayout.SOUTH);
	}
	
	public void setSubmitBtnAction(ActionListener l){
		submitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(l != null){
					l.actionPerformed(e);
				}
				status = 1;
				setVisible(false);
			}
		});
	}
	
	public void setContentPane(JPanel contentPane){
		setSize(contentPane.getWidth()+400, contentPane.getHeight()+80);
		add(contentPane, BorderLayout.CENTER);
	}
	
}
