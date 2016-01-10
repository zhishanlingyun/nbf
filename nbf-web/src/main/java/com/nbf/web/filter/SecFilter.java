package com.nbf.web.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Administrator
 * Date: 15-12-12
 * Time: 下午10:33
 */
public class SecFilter implements Filter {

    private static final Set<String> nologin = new HashSet<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String url1 = "/login";
        String url2 = "/login/req";
        String url3 = "*.jpg";
        String url4 = "*.css";
        String url5 = "*.png";
        String url6 = "*.js";
        String url7 = "*.gif";
        nologin.add(url1);
        nologin.add(url2);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String url = request.getServletPath();
        pass(url,nologin);
        if(!nologin.contains(url)){
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies){
            }
            response.sendRedirect("/login");
        }else{
            filterChain.doFilter(request,response);
        }

    }

    public boolean pass(String url,Set<String> set){
        boolean pass = false;
        url = url.substring(url.lastIndexOf("."),url.length()-1);
        /*if(){
            // *.jsp,*.js ...

        }*/
        return false;

    }

    @Override
    public void destroy() {
    }
}
