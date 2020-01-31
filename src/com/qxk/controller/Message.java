package com.qxk.controller;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.qxk.model.Blah;
import com.qxk.model.UserService;

@WebServlet(urlPatterns = { 
    "/message.do" }, 
    initParams = { 
        @WebInitParam(
            name = "MEMBER_VIEW", value = "member.jsp"
        )
    }
)
public class Message extends HttpServlet {
    private String MEMBER_VIEW;

    @Override
    public void init() throws ServletException {
        MEMBER_VIEW = getServletConfig().getInitParameter("MEMBER_VIEW");
    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("login");
        
        UserService userService = (UserService) getServletContext()
                .getAttribute("userService");

        Blah blah = new Blah();
        blah.setUsername(username);
        
        String blabla = request.getParameter("blabla");
        if (blabla != null && blabla.length() != 0) {
        	//限制字数140以内
            if (blabla.length() < 140) {
                blah.setDate(new Date());
                blah.setTxt(blabla);
                userService.addBlah(blah);
            }
            else {
                request.setAttribute("blabla", blabla);
            }
        }
        
        List<Blah> blahs = userService.getBlahs(blah);
        request.setAttribute("blahs", blahs);
        request.getRequestDispatcher(MEMBER_VIEW).forward(request, response);
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
