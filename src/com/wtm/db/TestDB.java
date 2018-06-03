package com.wtm.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {
	 public static void main(String[] a)
	            throws Exception {
	        Connection conn = DriverManager.
	            getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
	        // add application code here
	        Thread.sleep(10000);
	        conn.close();
	    }
}
