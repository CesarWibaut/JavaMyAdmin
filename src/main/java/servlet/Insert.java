package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Insert
 */
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
    


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = "Insert into " + request.getParameter("table") + "(";
		String values = "(";
		Connection con = (Connection) getServletContext().getAttribute("con");
		Enumeration<String> params = request.getParameterNames();
		
		while(params.hasMoreElements()) {
			
			String paramName = params.nextElement();
			if(!paramName.equals("table")) {
				query += paramName + (params.hasMoreElements() ? ", " : "");
				values += "'" +request.getParameter(paramName) +"'" + (params.hasMoreElements() ? ", " : "");
			}
		}
		try {
			System.out.println(query + ") values " + values + ");");
			con.prepareStatement(query + ") values " + values + ");").executeUpdate();
			response.sendRedirect("servlet-Select?table="+request.getParameter("table"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
