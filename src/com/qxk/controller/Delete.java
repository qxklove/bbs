package com.qxk.controller;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.qxk.model.Blah;
import com.qxk.model.UserService;

@WebServlet(
    urlPatterns={"/delete.do"},
    initParams={
        @WebInitParam(name = "SUCCESS_VIEW", value = "message.do")
    }
)
public class Delete extends HttpServlet {
    private String SUCCESS_VIEW;
    
	@Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
    }

    protected void doGet(HttpServletRequest request, 
	                     HttpServletResponse response) 
	                         throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("login");
        String message = request.getParameter("message"); 
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        Blah blah = new Blah();
        blah.setUsername(username);
        blah.setDate(new Date(Long.parseLong(message)));
        userService.deleteBlah(blah);
        response.sendRedirect(SUCCESS_VIEW);
	}
}
