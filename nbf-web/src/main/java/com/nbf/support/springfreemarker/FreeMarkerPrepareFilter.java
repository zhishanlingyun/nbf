package com.nbf.support.springfreemarker;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-11-28
 * Time: 下午5:22
 * To change this template use File | Settings | File Templates.
 */
public class FreeMarkerPrepareFilter implements Filter {

    private String cssPath;
    private String jsPath;
    private String imagePath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        cssPath = filterConfig.getInitParameter("cssPath");
        jsPath = filterConfig.getInitParameter("jsPath");
        imagePath = filterConfig.getInitParameter("imagePath");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String ctxPath = request.getContextPath();
        request.setAttribute("ctxPath",ctxPath );
        request.setAttribute("cssPath", ctxPath+cssPath);
        request.setAttribute("imagePath", ctxPath+imagePath);
        request.setAttribute("jsPath", ctxPath+jsPath);
        //request.setAttribute("decorate_info_path", request.getRequestURI());
        filterChain.doFilter(request,servletResponse);
        System.out.println("after doFilter");
    }

    @Override
    public void destroy() {
    }
}
