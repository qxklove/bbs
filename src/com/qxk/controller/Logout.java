package com.qxk.controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(
    urlPatterns={"/logout.do"},
    initParams={
        @WebInitParam(name = "LOGIN_VIEW", value = "index.jsp")
    }
)
public class Logout extends HttpServlet {
    private String LOGIN_VIEW;

    @Override
    public void init() throws ServletException {
        LOGIN_VIEW = getServletConfig().getInitParameter("LOGIN_VIEW");
    }
    
	protected void doGet(HttpServletRequest request,
	                     HttpServletResponse response) 
	                      throws ServletException, IOException {
	    request.getSession().invalidate();
        response.sendRedirect(LOGIN_VIEW);
	}
}
