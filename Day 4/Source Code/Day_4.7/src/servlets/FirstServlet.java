package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/first")
public class FirstServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String name = request.getParameter("Name");
		HttpSession session = request.getSession(  );
		if( session.isNew())
		{
			session.setAttribute("Name", name);
			session.setAttribute("User", "sandeep");
			session.setAttribute("Password", "sandeep");
		}
		
		response.setContentType("text/html");
		try( PrintWriter out = response.getWriter())
		{
			out.println("<html>");
			out.println("<head>");
			out.println("<title>First Servlet</title>");
			out.println("</head>");
			out.println("<body>");
			String url = response.encodeURL("second");
			out.println("<form action='"+url+"'>");
			out.println("<h3>Welcome,"+name+"</h3><br/>");
			out.println("<input type='submit' value='Next'/>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
		}
	}
}
