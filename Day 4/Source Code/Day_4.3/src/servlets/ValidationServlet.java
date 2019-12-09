package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import pojo.User;

public class ValidationServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private UserDao dao;
	@Override
	public void init(ServletConfig config) throws ServletException 
	{
		try
		{
			super.init(config);
			ServletContext context = this.getServletContext();
			String driver =  context.getInitParameter("DRIVER");
			String url =  context.getInitParameter("URL");
			String user =  context.getInitParameter("USER");
			String password =  context.getInitParameter("PASSWORD");
			
			this.dao = new UserDao( driver, url, user, password );
		} 
		catch (Exception cause) 
		{
			throw new ServletException( cause );
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ServletConfig config = this.getServletConfig();
		
		String email = request.getParameter("txtEmail");
		String password = request.getParameter("txtPassword");
		
		response.setContentType("text/html");
		try( PrintWriter out = response.getWriter();)
		{
			User user = dao.validate(email, password);
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Validation Servlet</title>");
			out.println("</head>");
			out.println("<body>");
			if( user != null )
			{
				out.println("<h3>User Id : "+user.getUserId()+"</h3><br/>");
				out.println("<h3>Full Name : "+user.getFullName()+"</h3><br/>");
				out.println("<h3>Birth Date : "+user.getBirthDate()+"</h3><br/>");
				out.println("<h3>Creation Date : "+user.getCreationDate()+"</h3><br/>");
				out.println("<h3><a href='Login.html'>Logout</a></h3><br/>");
			}
			else
			{
				out.println("<h3>Invalid email or password.<a href='Index.html'>Retry</a></h3><br/>");
			}
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
