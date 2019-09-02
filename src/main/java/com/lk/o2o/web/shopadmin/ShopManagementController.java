package com.lk.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lk.o2o.dto.ShopExecution;
import com.lk.o2o.entity.Area;
import com.lk.o2o.entity.PersonInfo;
import com.lk.o2o.entity.Shop;
import com.lk.o2o.entity.ShopCategory;
import com.lk.o2o.enums.ShopStateEnum;
import com.lk.o2o.service.AreaService;
import com.lk.o2o.service.ShopCategoryService;
import com.lk.o2o.service.ShopService;
import com.lk.o2o.util.CodeUtil;
import com.lk.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    @RequestMapping(value = "getshopbyid",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getShopById(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        try{
            long shopId = HttpServletRequestUtil.getLong(request, "shopId");
            Shop shop = shopService.getShopById(shopId);
            List<Area> areaList = areaService.getAreaList();
            modelMap.put("shop",shop);
            modelMap.put("areaList",areaList);
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "getshopinitinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getshopinitinfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        List<Area> areaList = new ArrayList<Area>();
        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
        try {
            areaList = areaService.getAreaList();
            shopCategoryList = shopCategoryService.getShopCategory(new ShopCategory());
            modelMap.put("success",true);
            modelMap.put("areaList",areaList);
            modelMap.put("shopCategoryList",shopCategoryList);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "/registershop",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> register(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String,Object>();
        //接收并转化相应的参数，包含店铺信息和图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        // 使用jackson,把前端传过来的json数据封装到pojo中
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            //封装数据到shop里
            shop = mapper.readValue(shopStr, Shop.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        //先判断传过来的验证码
        if(CodeUtil.checkVerifyCode(request)){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入正确的验证码");
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if(commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","上传图片为空");
            return modelMap;
        }
        //注册店铺
        if(shop != null && shopImg != null){
            //先自定义用户信息，后期从session获取
            PersonInfo owner = new PersonInfo();
            owner.setUserId(8l);
            shop.setOwner(owner);
            ShopExecution shopExecution = null;
            try {
                shopExecution = shopService.addShop(shop, shopImg.getInputStream(),shopImg.getOriginalFilename());
                if(ShopStateEnum.CHECK.getStatue() == shopExecution.getState()){
                    modelMap.put("success",true);
                }else{
                    modelMap.put("success",false);
                    modelMap.put("errMsg",shopExecution.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
                return modelMap;
            }
            return modelMap;
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入店铺信息");
            return modelMap;
        }
    }
}
