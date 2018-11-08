package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Update
 */
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con = (Connection) getServletContext().getAttribute("con");
		PrintWriter out = response.getWriter();
		
		out.println("<form action=updateData method=POST class=\"form text-center\">");
		
		Enumeration<String> params = request.getParameterNames();
		HashMap<String,String> map = new HashMap<String,String>();
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			map.put(param, request.getParameter(param));
			if(!param.equals("table")) {
				out.println("<h2>" + param+ "<h2>");
				out.println("<input type=text name=\""+param+"\" value=\""+request.getParameter(param)+"\" ><br>");
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute("oldValues", map);
		out.println("<input type=submit value=Submit>");
		out.println("</form></body></html>");
	}


}
