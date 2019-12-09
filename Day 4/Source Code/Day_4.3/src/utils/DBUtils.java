package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils
{
	public static Connection getConnection(String driver, String url, String user, String password)throws Exception 
	{
		Class.forName(driver);
		return DriverManager.getConnection(url, user, password);
	}
}
