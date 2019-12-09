package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import pojo.User;

public class RegistrationServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private UserDao dao;
	@Override
	public void init(ServletConfig config) throws ServletException 
	{
		try
		{
			super.init(config);
			String driver =  config.getInitParameter("DRIVER");
			String url =  config.getInitParameter("URL");
			String user =  config.getInitParameter("USER");
			String password =  config.getInitParameter("PASSWORD");
			
			this.dao = new UserDao( driver, url, user, password );
		} 
		catch (Exception cause) 
		{
			throw new ServletException( cause );
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		User user = new User();
		user.setFullName( request.getParameter("txtFullName") );
		user.setEmail( request.getParameter("txtEmail") );
		user.setPassword( request.getParameter("txtPassword"));
		user.setBirthDate(Date.valueOf(request.getParameter("txtBirthDate")));
		
		response.setContentType("text/html");
		try( PrintWriter out = response.getWriter();)
		{
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Registration Servlet</title>");
			out.println("</head>");
			out.println("<body>");
			dao.registerUser( user );
			out.println("<h3>Registration is successful.<a href='Login.html'>Login</a></h3><br/>");
			out.println("</body>");
			out.println("</html>");
		}
		catch (Exception cause) 
		{
			throw new ServletException(cause);
		}
	}
	@Override
	public void destroy() throws RuntimeException
	{
		try
		{
			this.dao.close();
		} 
		catch (IOException cause )
		{
			throw new RuntimeException(cause);
		}
	}
}
