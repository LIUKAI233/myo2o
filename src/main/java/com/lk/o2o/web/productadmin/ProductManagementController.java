package com.lk.o2o.web.productadmin;

import com.lk.o2o.dto.ProductCategoryExecution;
import com.lk.o2o.dto.Result;
import com.lk.o2o.entity.ProductCategory;
import com.lk.o2o.entity.Shop;
import com.lk.o2o.enums.ProductCategoryStateEnum;
import com.lk.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/productadmin")
public class ProductManagementController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /*查询店铺商品类别*/
    @RequestMapping(value = "getproductcategorylist",method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request){
        Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
        //判断店铺信息是否存在
        if (currentShop == null && currentShop.getShopId() <= 0){
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false,ps.getStateInfo(),ps.getState());
        }else {
            List<ProductCategory> productCategoryList = productCategoryService.queryProductCategory(currentShop.getShopId());
            return new Result<List<ProductCategory>>(true,productCategoryList);
        }
    }

    /*批量添加店铺类别*/
    @RequestMapping(value = "addproductcategories",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addProductCategories(@RequestBody List<ProductCategory> productCategories, HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        /*从session获取shopId，并添加进分类里面*/
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        if(currentShop == null && currentShop.getShopId() == null){
            modelMap.put("success",false);
            modelMap.put("errMsg","登录超时，请重新登录");
        }
         for(ProductCategory pc: productCategories) {
            pc.setShopId(currentShop.getShopId());
        }
        try {
            if(productCategories == null && productCategories.size() <= 0){
                modelMap.put("success",false);
                modelMap.put("errMsg","请至少输入一个类别");
            }else {
                ProductCategoryExecution pe = productCategoryService.addProductCategorys(productCategories);
                if (ProductCategoryStateEnum.SUCCESS.getState() == pe.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", ProductCategoryStateEnum.INNER_ERROR.getStateInfo());
                }
            }
        } catch (Exception e) {
           modelMap.put("success",false);
           modelMap.put("errMsg",e.getMessage());
           return modelMap;
        }
        return modelMap;
    }


    /*删除店铺类别*/
    @RequestMapping(value = "removeproductcategory",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> removeProductCategory(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        return modelMap;
    }
}
