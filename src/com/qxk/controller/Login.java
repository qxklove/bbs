package com.qxk.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.qxk.model.Account;
import com.qxk.model.UserService;

@WebServlet(
    urlPatterns={"/login.do"},
    initParams={
        @WebInitParam(name = "SUCCESS_VIEW", value = "message.do"),
        @WebInitParam(name = "ERROR_VIEW", value = "index.jsp")
    }
)
public class Login extends HttpServlet {
    private String SUCCESS_VIEW;
    private String ERROR_VIEW;
    
    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
        ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
    }
    
	protected void doPost(HttpServletRequest request, 
	                      HttpServletResponse response) 
	                        throws ServletException, IOException {
		String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    
	    String page = null;
	    Account account = new Account();
	    account.setName(username);
	    account.setPassword(password);
	    
	    UserService userService = (UserService) getServletContext().getAttribute("userService");
	    if(userService.checkLogin(account)) {
	        request.getSession().setAttribute("login", account.getName());
	        page = SUCCESS_VIEW;
	    }
	    else {
	        request.setAttribute("error", "名称或密码错误");
	        page = ERROR_VIEW;
	    }
	    request.getRequestDispatcher(page).forward(request, response);
	}
}
