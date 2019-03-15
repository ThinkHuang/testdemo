package cn.huang.tools;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class jdbcUtils {
	
	private static ComboPooledDataSource dateSource = new ComboPooledDataSource();
	
	public static Connection getConnection() throws SQLException{
		
		return dateSource.getConnection();
	}
	
	public static ComboPooledDataSource getDateSource(){
		
		return dateSource;
	}
}
