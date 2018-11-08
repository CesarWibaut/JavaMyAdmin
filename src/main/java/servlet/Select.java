package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Select
 */
public class Select extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection con = (Connection) getServletContext().getAttribute("con");
		PrintWriter out = response.getWriter();

		out.println("<table border=1>");
		String table = request.getParameter("table") == null ? "fournisseurs" : request.getParameter("table");
		String query = "Select * from " + table;

		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);

			System.out.println(ps);
			ResultSet rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			out.println("<tr>");
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				out.println("<th>" + rsmd.getColumnLabel(i) + "</th>");
			}out.println("<th>DEL</th>");
			out.println("</tr>");
			String cle = "", value="";
			String url="servlet-Delete?table="+table;
			while (rs.next()) {
				out.println("<tr>");
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					out.println("<td>" + rs.getString(i) + "</td> ");
					url+="&"+rsmd.getColumnLabel(i)+"="+rs.getString(i);
				}

				out.println("<td><a href=\"" + url + "\">DEL</a></td>");
				url = "servlet-Delete?table="+table;
				out.println("</tr>");
			}
			out.println("</table>");


			try {
				out.println("<h2> Insert dans la table : " + table + "</h2>");
				out.println("<form method=POST action=servlet-Insert>");
				out.println("<input type=hidden name=table value=" + table + ">");
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					out.println(
							rsmd.getColumnLabel(i) + " : " + "<input type=text name=" + rsmd.getColumnLabel(i) + ">");
				}
				out.println("<input type=Submit value=Submit>");
				out.println("</form></body></html>");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
