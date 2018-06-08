package com.wtm.db;

import org.apache.commons.dbutils.QueryRunner;

public class BasicDB {
	public QueryRunner qr;
	public BasicDB(){
		MyDataSource ds = new MyDataSource("jdbc:h2:~/wtm_db", "sa", "");
		//MyDataSource ds = new MyDataSource("jdbc:h2:tcp://10.236.102.250:9092/~/wtm_db", "sa", "");
		 qr = new QueryRunner(ds);
	}
}
