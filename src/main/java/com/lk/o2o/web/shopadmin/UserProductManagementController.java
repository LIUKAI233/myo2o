package com.lk.o2o.web.shopadmin;

import com.lk.o2o.dto.EchartSeries;
import com.lk.o2o.dto.EchartXaxis;
import com.lk.o2o.dto.UserProductMapExecution;
import com.lk.o2o.entity.Product;
import com.lk.o2o.entity.ProductSellDaily;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/shopadmin")
public class UserProductManagementController {

    @Autowired
    private UserProductMapService userProductMapService;

    @Autowired
    private ProductSellDailyService productSellDailyService;

    @RequestMapping(value = "/listuserproductmapbyshop",method = RequestMethod.GET)
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

    @RequestMapping(value = "/listproductselldailyinfobyshop",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listProductSellDailyInfoByShop(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //从session中获取店铺信息
        Shop currentShop =(Shop) request.getSession().getAttribute("currentShop");
        //空值判断
        if (currentShop != null && currentShop.getShopId() != null){
            //添加查询信息
            ProductSellDaily productSellDailyCondition = new ProductSellDaily();
            productSellDailyCondition.setShop(currentShop);
            Calendar calendar = Calendar.getInstance();
            //获取昨天的日期
            calendar.add(Calendar.DATE,-1);
            Date endTime = calendar.getTime();
            //获取7天前的日期
            calendar.add(Calendar.DATE,-6);
            Date beginTime = calendar.getTime();
            //查询符合商品记录的集合
            List<ProductSellDaily> productSellDailyList = productSellDailyService.queryProductSellDailyList(productSellDailyCondition, beginTime, endTime);
            //指定日期格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //商品名列表，保证唯一性
            HashSet<String> legendData = new HashSet<>();
            //x轴数据
            HashSet<String> xData = new HashSet<>();
            //定义series
            ArrayList<EchartSeries> series = new ArrayList<>();
            //日销量列表
            ArrayList<Integer> totalList = new ArrayList<>();
            //当前商品名，默认为空
            String currentProductName = "";
            for (int i = 0 ; i < productSellDailyList.size() ; i++){
                ProductSellDaily productSellDaily = productSellDailyList.get(i);
                //自动去重
                legendData.add(productSellDaily.getProduct().getProductName());
                xData.add(sdf.format(productSellDaily.getCreateTime()));
                if(!currentProductName.isEmpty() && !currentProductName.equals(productSellDaily.getProduct().getProductName())){
                    //如果current不等于获取的商品名，或者已遍历到列表的末尾，且currentProductName不为空
                    //则是遍历到下一个商品的日销量信息了，将前一轮遍历的信息放入series当中，
                    //包括了商品名以及与商品名对应的统计日期以及当日销量
                    EchartSeries es = new EchartSeries();
                    es.setName(currentProductName);
                    es.setData(totalList.subList(0,totalList.size()));
                    series.add(es);
                    //重置totalList
                    totalList = new ArrayList<Integer>();
                    currentProductName = productSellDaily.getProduct().getProductName();
                    //继续添加新的值
                    totalList.add(productSellDaily.getTotal());
                }else{
                    //如果还是当前的productId则继续添加新值
                    totalList.add(productSellDaily.getTotal());
                    currentProductName = productSellDaily.getProduct().getProductName();
                }
                //队列之末，需要将最后的一个商品销量信息也添加上
                if(i == productSellDailyList.size()-1){
                    EchartSeries es = new EchartSeries();
                    es.setName(currentProductName);
                    es.setData(totalList.subList(0,totalList.size()));
                    series.add(es);
                }
            }
            modelMap.put("series",series);
            modelMap.put("legendData",legendData);
            //拼接出xAxis
            List<EchartXaxis> xAxis = new ArrayList<>();
            EchartXaxis exa = new EchartXaxis();
            exa.setData(xData);
            xAxis.add(exa);
            modelMap.put("xAxis",xAxis);
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","请重新登录");
        }
        return modelMap;
    }

}
