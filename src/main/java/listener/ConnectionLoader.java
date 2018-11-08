package listener;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ConnectionLoader
 *
 */
public class ConnectionLoader implements ServletContextListener {

    
	Connection con = null;
   

    public void contextDestroyed(ServletContextEvent sce)  { 
         try {
        	 System.out.println("CLOSED 2");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    public void contextInitialized(ServletContextEvent sce)  { 
    	
    	ClassLoader loader = Thread.currentThread().getContextClassLoader();   
		InputStream stream = loader.getResourceAsStream("data.properties");
		Properties prop = new Properties();
		try {
			prop.load(stream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url = prop.getProperty("url") + prop.getProperty("base");
		String id = prop.getProperty("user");
		String pwd = prop.getProperty("password");
		System.out.println(url + " " + id + " " + pwd);
    	
		try {
			con = DriverManager.getConnection(url, id, pwd);
			sce.getServletContext().setAttribute("con", con);
			System.err.println("Context loaded on path : " + sce.getServletContext().getContextPath());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
}
