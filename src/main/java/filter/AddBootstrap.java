package filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class AddBootstrap
 */
public class AddBootstrap implements Filter {

    

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		
		out.println("<!Doctype html>\n" + 
				"<html lang=\"en\">\n" + 
				"<head>\n" + 
				"<meta charset=\"utf-8\">\n" + 
				"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" + 
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + 
				"<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">" + 
				"</head>" +
				"<body>");
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	public void destroy() {
		
	}
	
	

}
