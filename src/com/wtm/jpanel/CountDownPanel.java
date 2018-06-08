package com.wtm.jpanel;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CountDownPanel extends JPanel{
	
	private JLabel btnLabel = new JLabel();
	private JLabel textLabel = new JLabel();
	public CountDownPanel () {
		add(textLabel); 
		add(btnLabel);
	}
	
	public void update(String text){
		textLabel.setText(text);
	}
	
}
