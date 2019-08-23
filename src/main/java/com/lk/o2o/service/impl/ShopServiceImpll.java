package com.lk.o2o.service.impl;

import com.lk.o2o.dao.ShopDao;
import com.lk.o2o.dto.ShopExecution;
import com.lk.o2o.entity.Shop;
import com.lk.o2o.enums.ShopStateEnum;
import com.lk.o2o.exceptions.ShopOperationException;
import com.lk.o2o.service.ShopService;
import com.lk.o2o.util.FileUtil;
import com.lk.o2o.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;

@Service
@Transactional  //开启事务
public class ShopServiceImpll implements ShopService {
    @Autowired
    private ShopDao shopDao;
    @Override
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) {
        if(shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        //给店铺附加初始值
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(0);
        //调用方法，添加店铺
        int effectedNum = shopDao.insertShop(shop);
        if (effectedNum <= 0) {
            throw new ShopOperationException("店铺创建失败");
        }else{
            try {
                //创建图片保存位置，并保存图片路径
                addShopImg(shop,shopImgInputStream, fileName);
            }catch (Exception e){
                throw new ShopOperationException("addShopImg ERROR"+e.getMessage());
            }
            effectedNum = shopDao.updateShop(shop);
            if (effectedNum <= 0){
                throw new ShopOperationException("店铺图片路径更新失败");
            }
        }
        return new ShopExecution(ShopStateEnum.SUCCESS,shop);
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream , String fileName) {
        //获取shop图片目录的相对路径
        String dest = FileUtil.getShopImagePath(shop.getShopId());
        //传入图片文件和图片所在相对路径
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream,dest,fileName);
        shop.setShopImg(shopImgAddr);
    }
}
