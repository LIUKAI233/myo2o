package com.lk.o2o.interceptor.shopadmin;

import com.lk.o2o.entity.Shop;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 店家管理系统，操作验证拦截器
 */
public class ShopPermissionInterceptor extends HandlerInterceptorAdapter {
    /*
     * 主要做事前拦截，即用户操作发生前改写perHandle里的逻辑，进行拦截
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //通过session获取当前所选择的店铺
        Shop currentShop =(Shop) request.getSession().getAttribute("currentShop");
        //获取当前账号可操作的店铺列表
        List<Shop> shopList =(List<Shop>) request.getSession().getAttribute("shopList");
        //判断是否为空
        if(currentShop != null && shopList != null){
            //遍历可操作店铺列表
            for (Shop shop : shopList) {
                //如果当前选择的店铺在可操作店铺里，则返回true
                if (shop.getShopId() == currentShop.getShopId()){
                    return true;
                }
            }
        }
        return false;
    }
}
