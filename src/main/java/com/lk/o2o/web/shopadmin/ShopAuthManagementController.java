package com.lk.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lk.o2o.dto.ShopAuthMapExecution;
import com.lk.o2o.entity.Shop;
import com.lk.o2o.entity.ShopAuthMap;
import com.lk.o2o.enums.ShopAuthMapStateEnum;
import com.lk.o2o.service.ShopAuthMapService;
import com.lk.o2o.util.CodeUtil;
import com.lk.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopAuthManagementController {

    private ShopAuthMapService shopAuthMapService;

    @Autowired
    private void setShopAuthMapService(ShopAuthMapService shopAuthMapService){
        this.shopAuthMapService = shopAuthMapService;
    }


    //根据店铺id，获取该店铺下的所有员工授权信息
    @RequestMapping(value = "/listshopauthmaplistbyshopid",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listShopAuthMapListByShopId (HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //从前端获取分页信息
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        //从session中获取店铺信息
        Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
        //空值判断
        if ((pageIndex > -1) && (pageSize > -1) && currentShop != null && currentShop.getShopId() != null){
            //分页取出该店铺下的授权信息
            ShopAuthMapExecution shopAuthMapExecution = shopAuthMapService.listShopAuthMapListByShopId(currentShop.getShopId(), pageIndex, pageSize);
            modelMap.put("shopAuthMapList",shopAuthMapExecution.getShopAuthMapList());
            modelMap.put("count",shopAuthMapExecution.getCount());
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","pageSize is empty OR pageIndex is empty OR currentShop is empty");
        }
        return modelMap;
    }

    //根据id获取店铺授权信息
    @RequestMapping(value = "/getshopauthmapbyid",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getShopAuthMapById(@RequestParam("shopAuthId")Long shopAuthId){
        Map<String, Object> modelMap = new HashMap<>();
        //判空
        if (shopAuthId != null && shopAuthId > -1){
            ShopAuthMap shopAuthMap = shopAuthMapService.getShopAuthMapById(shopAuthId);
                modelMap.put("success",true);
                modelMap.put("shopAuthMap",shopAuthMap);
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","shopAuthId is empty");
        }
        return modelMap;
    }

    //更新店铺授权信息
    @RequestMapping(value = "/modifyshopauthmap",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> modifyShopAuthMap(String shopAuthMapStr,HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //是修改编辑，还是删除/恢复:前者需判断验证码，后者不需要
        //statusChange为false 跳过验证，为true 不跳过验证
        Boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");
        if(statusChange && CodeUtil.checkVerifyCode(request)){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入正确的验证码");
            return modelMap;
        }
        //将前台传过来的json字符串装换成ShopAuthMap实例
        ObjectMapper mapper = new ObjectMapper();
        ShopAuthMap shopAuthMap;
        try {
            shopAuthMap = mapper.readValue(shopAuthMapStr,ShopAuthMap.class);
        } catch (Exception e) {
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        //空值判断
        if (shopAuthMap != null && shopAuthMap.getShopAuthId() != null){
            try {
                //判断被操作的对象是否为店家，店家本身不支持修改
                if (checkPermission(shopAuthMap.getShopAuthId())){
                    modelMap.put("success",false);
                    modelMap.put("errMsg","店家本身不支持修改");
                    return modelMap;
                }
                ShopAuthMapExecution se = shopAuthMapService.modifyShopAuthMap(shopAuthMap);
                if (se.getState() == ShopAuthMapStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",se.getStateInfo());
                }
            }catch (RuntimeException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
                return modelMap;
            }
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入需要修改的信息");
        }
        return modelMap;
    }

    private boolean checkPermission(Long shopAuthId) {
        ShopAuthMap shopAuthMap = shopAuthMapService.getShopAuthMapById(shopAuthId);
        //表示是店家本身，不能操作
        return shopAuthMap.getTitleFlag() == 0;
    }

}
