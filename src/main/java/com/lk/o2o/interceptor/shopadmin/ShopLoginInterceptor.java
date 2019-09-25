package com.lk.o2o.interceptor.shopadmin;

import com.lk.o2o.entity.PersonInfo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 店家管理系统登录验证拦截器
 */
public class ShopLoginInterceptor extends HandlerInterceptorAdapter {
    /*
    * 主要做事前拦截，即用户操作发生前改写perHandle里的逻辑，进行拦截
    */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //从session中获取用户信息
        Object userObj = request.getSession().getAttribute("user");
        if(userObj != null){
            //将获取到的用户信息，转换成personInfo实体类对象，并进一步验证
            PersonInfo user = (PersonInfo)userObj;
            if(user != null && user.getUserId() != null && user.getUserId() > 0 && user.getEnableStatus() == 1){
                //若通过验证侧返回true，拦截器返回true之后，用户才可以进行操作
                return true;
            }
        }
        //若不满足登录验证，则直接跳转到登录页面
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<script>");
        out.println("window.open('"+request.getContextPath()+"/local/login?usertype=2','_self')");
        out.println("</script>");
        out.println("</html>");
        return false;
    }
}
