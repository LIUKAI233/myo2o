package com.lk.o2o.web.shopadmin;

import com.lk.o2o.dto.AwardExecution;
import com.lk.o2o.entity.Award;
import com.lk.o2o.entity.Shop;
import com.lk.o2o.enums.AwardStateEnum;
import com.lk.o2o.service.AwardService;
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
@RequestMapping("/shopadmin")
public class AwardManagementController {
    @Autowired
    private AwardService awardService;

    /*通过传入的条件分页查询奖品信息列表*/
    @RequestMapping(value = "/listawards",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listAwards(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //获取分页信息
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        //获取模糊查询的奖品名
        String awardName = HttpServletRequestUtil.getString(request, "awardName");
        //从session里获取店铺信息
        Shop currentShop =(Shop) request.getSession().getAttribute("currentShop");
        //空值判断
        if (currentShop != null && currentShop.getShopId() != null && pageIndex > 0 && pageSize > 0){
            Award awardCondition = new Award();
            awardCondition.setShopId(currentShop.getShopId());
            if(awardName != null){
                awardCondition.setAwardName(awardName);
            }
            //查询列表
            AwardExecution ae = awardService.queryAwardList(awardCondition, pageIndex, pageSize);
            if (ae.getState() == AwardStateEnum.SUCCESS.getState()){
                modelMap.put("success",true);
                modelMap.put("awardList",ae.getAwardList());
                modelMap.put("count",ae.getCount());
            }else {
                modelMap.put("success",false);
                modelMap.put("errMsg",ae.getStateInfo());
            }
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","信息错误");
        }
        return modelMap;
    }
}
