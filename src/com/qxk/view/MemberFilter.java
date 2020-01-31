package com.qxk.view;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(
    urlPatterns = { "/delete.do", "/logout.do", "/message.do", "/member.jsp" }, 
    initParams = { @WebInitParam(name = "LOGIN_VIEW", value = "index.html") })
public class MemberFilter implements Filter {
    private String LOGIN_VIEW;
    public void init(FilterConfig config) throws ServletException {
        this.LOGIN_VIEW = config.getInitParameter("LOGIN_VIEW");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request; 
        if(req.getSession().getAttribute("login") != null) {
            chain.doFilter(request, response);
        }
        else {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect(LOGIN_VIEW);
        }
    }

    public void destroy() {
    }

}
