package com.qxk.view;

import java.util.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;

import com.qxk.model.*;

@WebListener
public class BbsListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
    	try {
    		Context initContext=new InitialContext();
    		Context envContext =(Context)initContext.lookup("java:/comp/env");
    		DataSource dataSource=(DataSource)envContext.lookup("jdbc/bbs");
    		
    		ServletContext context=sce.getServletContext();
    		context.setAttribute("userService", new UserService(
    												new AccountDAOJdbcImpl(dataSource),
    												new BlahDAOJdbcImpl(dataSource)));
    	}catch (NamingException ex) {
			throw new RuntimeException(ex);
		}
    }

    public void contextDestroyed(ServletContextEvent sce) {}
}
