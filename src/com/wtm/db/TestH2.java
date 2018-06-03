package com.wtm.db;

import java.sql.SQLException;

import org.h2.tools.Server;

public class TestH2 {
	public static void  main(String[] args){
		// start the TCP Server
		Server server = null;
		try {
			server = Server.createTcpServer(args).start();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// stop the TCP Server
		server.stop();
	}
}
