package com.qxk.view;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "ENCODING", value = "UTF-8")
		})
public class EncodingFilter implements Filter {
    private String ENCODING;
    
    public void init(FilterConfig config) throws ServletException {
        ENCODING = config.getInitParameter("ENCODING");
    }
    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    HttpServletRequest req = (HttpServletRequest) request;
	    if("GET".equals(req.getMethod())) {
	        req = new EncodingWrapper(req, ENCODING);
	    }
	    else {
	        req.setCharacterEncoding(ENCODING);
	    }
		chain.doFilter(req, response);
	}

	public void destroy() {}

}
