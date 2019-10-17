package com.lk.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lk.o2o.dto.AwardExecution;
import com.lk.o2o.dto.ImageHolder;
import com.lk.o2o.entity.Award;
import com.lk.o2o.entity.Shop;
import com.lk.o2o.enums.AwardStateEnum;
import com.lk.o2o.service.AwardService;
import com.lk.o2o.util.CodeUtil;
import com.lk.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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

    @RequestMapping(value = "/modifyaward",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> modifyAward(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //获取修改状态，是否需要输入验证码
        Boolean changeState = HttpServletRequestUtil.getBoolean(request, "changeState");
        if (changeState && CodeUtil.checkVerifyCode(request)){
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入正确的验证码");
            return modelMap;
        }
        //从session中获取店铺信息
        Shop currentShop =(Shop) request.getSession().getAttribute("currentShop");
        if(currentShop != null && currentShop.getShopId() != null){
            //奖品图片集合
            ImageHolder imageHoleder = null;
            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            try {
                //若存在中存在文件流，取出其中的文件，包括缩略图和详情图
                if (commonsMultipartResolver.isMultipart(request)){
                    MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                    //获取图片信息
                    MultipartFile thumbnailFile = multipartHttpServletRequest.getFile("thumbnail");
                    if (thumbnailFile != null){
                        imageHoleder = new ImageHolder(thumbnailFile.getInputStream(),thumbnailFile.getName());
                    }
                }
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
                return modelMap;
            }
            try {
                //从JSON中获取奖品修改信息
                String awardStr = HttpServletRequestUtil.getString(request, "awardStr");
                ObjectMapper mapper = new ObjectMapper();
                Award award = mapper.readValue(awardStr, Award.class);
                //传入奖品修改信息，店铺id，图片信息
                AwardExecution ae = awardService.modifyAward(award, currentShop.getShopId(), imageHoleder);
                if (ae.getState() == AwardStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else{
                    modelMap.put("success",false);
                    modelMap.put("errMsg",ae.getStateInfo());
                }
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
                return modelMap;
            }
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","请重新登录");
        }
        return modelMap;
    }
}
