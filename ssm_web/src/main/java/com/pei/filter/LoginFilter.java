package com.pei.filter;


import com.pei.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if ("/ssm_web/".equals(request.getRequestURI()) || request.getRequestURI().contains("/login.jsp")||request.getRequestURI().contains("error")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            if (user != null) {
                chain.doFilter(req, resp);
            } else {
                response.sendRedirect(request.getContextPath() + "/error/error.html");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
