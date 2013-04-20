package by.bsuir.facultative.filter;

import javax.servlet.*;

import org.apache.log4j.Logger;

import java.io.IOException;

public class EncodingFilter implements Filter {
	private static Logger logger = Logger.getLogger(EncodingFilter.class);
	public void init(FilterConfig arg0) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		logger.debug("filter");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}