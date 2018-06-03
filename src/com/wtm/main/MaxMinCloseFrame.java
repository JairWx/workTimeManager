package com.wtm.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MaxMinCloseFrame extends JFrame{  
    public final static String TAG = "ProgrameTest";  
  
    public MaxMinCloseFrame(String host)  
    {  
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);  
        setLayout(null);  
        SwingUtilities.updateComponentTreeUI(this);  
        setTitle("最大化最小化测试");  
        this.setName("最大化最小化测试");  
          
        setSize(700,600);  
        this.setLocationRelativeTo(null);//居中显示  
        this.setResizable(false);  
        this.setAlwaysOnTop(true);  
         
        initComponent(host);  
          
    }  
      
    private void initComponent(String host)  
    {  
      this.addWindowListener(new WindowAdapter() {  
  
          @Override  
          public void windowIconified(WindowEvent e) {  
              setExtendedState(JFrame.NORMAL);  
          }  
        });  
        
    }  
     
    public static void main(String[] args) {  
        new MaxMinCloseFrame("192.168.0.1").setVisible(true);  
    }  
    
}  
