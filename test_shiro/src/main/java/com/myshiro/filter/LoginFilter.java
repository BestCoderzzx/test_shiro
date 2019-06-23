package com.myshiro.filter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lenovo on 2019/6/6.
 */
public class LoginFilter extends FormAuthenticationFilter {

    /**
     * 未登录授权的情况下访问
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("FormAuthenticationFilter onAccessDenied exe ===");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        //设置编码格式，header的content-type也要设置，否则浏览器不会以utf8解析，还是乱码。设置application/json可以让js不需要eval即可使用对象
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-type",
                "application/json;charset=UTF-8");
        PrintWriter out;
        try {
            out = httpServletResponse.getWriter();
            out.println("{\"code\":-1,\"msg\":\"未登录用户！\"}");
            out.flush();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }

//    @Override
//    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
//        System.out.println("onLoginFailure ===");
//        return false;
//    }
}
