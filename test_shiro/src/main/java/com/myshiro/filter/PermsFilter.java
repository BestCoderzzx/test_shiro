package com.myshiro.filter;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lenovo on 2019/6/6.
 * 授权拦截器
 */
public class PermsFilter extends PermissionsAuthorizationFilter{

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        System.out.println("用户未授权访问了该页面");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-type",
                "application/json;charset=UTF-8");
        PrintWriter out;
        try {
            out = httpServletResponse.getWriter();
            out.println("{\"code\":-1,\"msg\":\"登录用户无权执行该操作！\"}");
            out.flush();
            out.close();
        } catch (IOException e1) {
//            log.info(e1.getMessage());
            e1.printStackTrace();
        }
        return false;
    }
}
