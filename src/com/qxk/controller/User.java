package com.qxk.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.qxk.model.Account;
import com.qxk.model.Blah;
import com.qxk.model.UserService;


@WebServlet(
    urlPatterns = { "/user/*" }, 
    initParams = {
        @WebInitParam(name = "USER_VIEW", value = "/user.jsp") 
    }
)
public class User extends HttpServlet {	
    private String USER_VIEW;

    @Override
    public void init() throws ServletException {
        USER_VIEW = getServletConfig().getInitParameter("USER_VIEW");
    }
	protected void doGet(HttpServletRequest request, 
	                     HttpServletResponse response) 
	                         throws ServletException, IOException {
	    UserService userService = 
	        (UserService) getServletContext().getAttribute("userService");
	    
	    String username = request.getPathInfo().substring(1);
	    
	    Account account=new Account();
		account.setName(request.getParameter("username"));
		
	    if(userService.isUserExisted(account)) {
	        Blah blah = new Blah();
	        blah.setUsername(username);
	        List<Blah> blahs = userService.getBlahs(blah);
	        request.setAttribute("blahs", blahs);
	    }
        request.setAttribute("username", username);
        request.getRequestDispatcher(USER_VIEW).forward(request, response);
	}
}
