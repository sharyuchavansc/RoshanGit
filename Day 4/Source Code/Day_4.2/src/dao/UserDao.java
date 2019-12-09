package dao;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.User;
import utils.DBUtils;

public class UserDao implements Closeable
{
	private Connection connection;
	private PreparedStatement registerationStatement;
	private PreparedStatement validateStatement;
	public UserDao()throws Exception
	{ }
	public UserDao(String driver, String url, String user, String password)throws Exception
	{
		this.connection = DBUtils.getConnection( driver, url, user, password );
		this.registerationStatement = this.connection.prepareStatement("INSERT INTO users(full_name, email, password, birth_date, creation_date) VALUES(?,?,?,?,?)");
		this.validateStatement = this.connection.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
	}
	public int registerUser(User user)throws Exception
	{
		this.registerationStatement.setString(1, user.getFullName());
		this.registerationStatement.setString(2, user.getEmail());
		this.registerationStatement.setString(3, user.getPassword());
		this.registerationStatement.setDate(4, user.getBirthDate());
		this.registerationStatement.setDate(5, user.getCreationDate());
		return this.registerationStatement.executeUpdate();
	}
	public User validate( String email, String password )throws Exception
	{
		this.validateStatement.setString(1, email);
		this.validateStatement.setString(2, password);
		try( ResultSet rs = this.validateStatement.executeQuery())
		{
			if( rs.next())
			{
				User user = new User(rs.getInt("user_id"),rs.getString("full_name"), rs.getString("email"), rs.getString("password"), rs.getDate("birth_date"), rs.getDate("creation_date"));
				return user;
			}
		}
		return null;
	}
	@Override
	public void close() throws IOException 
	{
		try 
		{
			this.validateStatement.close();
			this.connection.close();
		} 
		catch (SQLException cause) 
		{
			throw new IOException( cause );
		}
	}
}
