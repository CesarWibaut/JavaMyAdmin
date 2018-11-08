package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateDB
 */
public class UpdateDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = (Connection) getServletContext().getAttribute("con");
		
		HttpSession session = request.getSession();
		
		@SuppressWarnings("unchecked")
		HashMap<String,String> map = (HashMap<String, String>) session.getAttribute("oldValues");
		String table = map.get("table");
		String query = "UPDATE " + table;
		String set = " SET ";
		String where = " WHERE ";
		
		map.remove("table");
		
		
		Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			set += param + "='" + request.getParameter(param)+"' " + (params.hasMoreElements() ? ", " : "");
		}
		
		for(String s : map.keySet()) {
			where += (!where.equals(" WHERE ") ? " AND " : "") + s + "='" + map.get(s)+"'";
		}
		
		try {
			con.prepareStatement(query + set + where).executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
		System.out.println(query + set + where);
		
		response.sendRedirect("servlet-Select?table=" +table);
		
	}

}
