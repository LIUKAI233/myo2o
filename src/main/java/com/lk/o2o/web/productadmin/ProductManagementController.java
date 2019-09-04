package com.lk.o2o.web.productadmin;

import com.lk.o2o.dto.Result;
import com.lk.o2o.entity.ProductCategory;
import com.lk.o2o.entity.Shop;
import com.lk.o2o.enums.ProductCategoryStateEnum;
import com.lk.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
}
