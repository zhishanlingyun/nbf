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
        nologin.add(url3);
        nologin.add(url4);
        nologin.add(url5);nologin.add(url6);
        nologin.add(url7);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String url = request.getServletPath();
        System.out.println("getServletPath"+url);
        System.out.println("url"+request.getRequestURL());

        /*if(!pass(url,nologin)){
            Cookie[] cookies = request.getCookies();

            response.sendRedirect("/login");
        }else{
            filterChain.doFilter(request,response);
        }*/
        filterChain.doFilter(request,response);

    }

    public boolean pass(String url,Set<String> set){
        if(set.contains(url)){
            return true;
        }
        url = url.substring(url.lastIndexOf("."),url.length());
        if(set.contains(("*"+url))){
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {
    }
}
