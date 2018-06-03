package com.wtm.main;


/**
 * 主程序
 * @author wenjie
 *
 */
public class Main {
	public static void main(String[] args) {
        Clock t = new Clock();  
        //t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        t.setAlwaysOnTop(true);//总是在程序的前面
        t.setVisible(true);  
        //t.setLocationRelativeTo(null);//窗体显示在屏幕中央  
    }  
}
