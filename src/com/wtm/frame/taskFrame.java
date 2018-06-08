package com.wtm.frame;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import com.wtm.task.NoticeTask;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import javax.swing.JLabel;

public class TaskFrame extends BasicFrame {

	//private JPanel contentPane;
	private JTextField textField;
	private Map<String,Object> dataPipeline;
	
	private ClockFrame cframe;

	/**
	 * Create the frame.
	 */
	public TaskFrame(ClockFrame cframe) {
		super();
		JPanel contentPane = new JPanel();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 353, 95);
		
		//setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.add(panel2, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("输入任务名称");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					new NoticeTask(textField.getText(),cframe);
					setVisible(false);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		panel.add(textField);
		textField.setColumns(10);
		setSubmitBtnAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String taskName = textField.getText();
				System.out.println(taskName);
				//dataPipeline.put("task_name", taskName);
				setVisible(false);
				new NoticeTask(taskName,cframe);
			}
		});
		panel.setSize(50, 50);
		setContentPane(panel);
	}

}
