package com.lk.o2o.web.shopadmin;

import com.lk.o2o.dto.UserProductMapExecution;
import com.lk.o2o.entity.Product;
import com.lk.o2o.entity.Shop;
import com.lk.o2o.entity.UserProductMap;
import com.lk.o2o.enums.UserProductMapEnum;
import com.lk.o2o.service.ProductSellDailyService;
import com.lk.o2o.service.UserProductMapService;
import com.lk.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shopamin")
public class UserProductManagementController {

    @Autowired
    private UserProductMapService userProductMapService;

    @Autowired
    private ProductSellDailyService productSellDailyService;

    @RequestMapping(value = "listuserproductmapbyshop",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listUserProductMapsByShop(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //从前端获取分页信息
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        //从session中获取店铺信息
        Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
        if (pageIndex > 0 && pageSize > 0 && currentShop != null && currentShop.getShopId() != null){
            //添加查询条件
            UserProductMap userProductMap = new UserProductMap();
            userProductMap.setShop(currentShop);
            //从前端获取产品名
            String productName = HttpServletRequestUtil.getString(request, "productName");
            if(productName != null){
                //用户输入了产品名
                Product product = new Product();
                product.setProductName(productName);
                userProductMap.setProduct(product);
            }
            //根据传入的条件，查询信息
            UserProductMapExecution ue = userProductMapService.listUserProductMaps(userProductMap, pageIndex, pageSize);
            if (ue.getState() == UserProductMapEnum.SUCCESS.getState()){
                modelMap.put("success",true);
                modelMap.put("count",ue.getCount());
                modelMap.put("userProductMapList",ue.getUserProductMapList());
            }else{
                modelMap.put("success",false);
                modelMap.put("errMsg",ue.getStateInfo());
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入完整的信息");
        }
        return modelMap;
    }

}
