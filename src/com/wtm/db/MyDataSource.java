package com.wtm.db;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;


public class MyDataSource implements DataSource{
	
	private String jdbcUrl = null;
	private Properties properties = new Properties();//用户名和密码配置 因为需要clone所以需要初始化
	private String driverClass = "org.h2.Driver";//驱动名称
	private Driver driver = null;//驱动
	
	public MyDataSource (String jdbcUrl,String user, String password){
		this.jdbcUrl = jdbcUrl;
		this.properties = overrideProps(user, password);
	}
	
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * 关键
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection out = driver().connect( jdbcUrl, this.properties ); 
        if (out == null)
            throw new SQLException("Apparently, jdbc URL '" + jdbcUrl + "' is not valid for the underlying " +
                            "driver [" + driver() + "].");
        return out;
		// TODO Auto-generated method stub
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		
        Connection out = driver().connect( jdbcUrl, overrideProps(username,password) ); 
        if (out == null)
            throw new SQLException("Apparently, jdbc URL '" + jdbcUrl + "' is not valid for the underlying " +
                            "driver [" + driver() + "].");
        return out;
	}
	
	private final Properties overrideProps(String user, String password)
    {
        Properties overriding = (Properties) properties.clone(); //we are relying on a defensive clone in our base class!!!
        overriding.put("user", user);
        overriding.put("password", password);
        return overriding;
    }
	
	private synchronized Driver driver() throws SQLException
    {
        if (driver == null)
        {
        	driver = DriverManager.getDriver( jdbcUrl );
        }
        return driver;
    }

}
