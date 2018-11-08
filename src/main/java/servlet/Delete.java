package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String table = req.getParameter("table");
		String query = "Delete from " + table + " where ";
		
		Connection con = (Connection)getServletContext().getAttribute("con");
		
		Enumeration<String> params = req.getParameterNames();
		
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			
			if(!param.equals("table")) {
				query += param + " = '" + req.getParameter(param) +"'";
				query += params.hasMoreElements() ? " AND " : " ;"; 
			}
		}
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
		//	ps.setInt(1, Integer.valueOf(value));
			System.out.println(query );
			ps.executeUpdate();
			resp.sendRedirect("servlet-Select?table="+table);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
