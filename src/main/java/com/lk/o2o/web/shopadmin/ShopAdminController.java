package com.lk.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shopadmin",method = RequestMethod.GET)
public class ShopAdminController {

    @RequestMapping(value = "/shopoperation")
    public String shopOperation(){
        //转发至店铺添加/修改界面
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList(){
        //转发至店铺列表展示界面
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanage")
    public String shopManage(){
        //转发至店铺管理界面
        return "shop/shopmanage";
    }

    @RequestMapping(value = "/shopauthmanagement")
    public String shopAuthManagement(){
        //转发至店铺权限管理界面
        return "shop/shopauthmanagement";
    }

    @RequestMapping(value = "/shopauthedit")
    public String shopAuthEdit(){
        //转发至店铺权限修改界面
        return "shop/shopauthedit";
    }

    @RequestMapping(value = "/productbuycheck")
    public String productBuyCheck(){
        //跳转到店铺销售记录页面
        return "shop/productbuycheck";
    }

    @RequestMapping(value = "/awardmanagement")
    public String awardManagement(){
        //跳转到奖品管理页面
        return "shop/awardmanagement";
    }

    @RequestMapping(value = "/awardoperation")
    public String awardOperation(){
        //跳转到奖品编辑页面
        return "shop/awardoperation";
    }

    @RequestMapping(value = "/userpoint")
    public String userPoint(){
        //跳转到顾客积分情况页面
        return "shop/userpoint";
    }
}
