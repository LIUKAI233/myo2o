package com.lk.o2o.util;

import com.google.code.kaptcha.Constants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CodeUtil {
    /**
     * 校验验证码是否和预期相符
     * @param request 请求信息
     * @return 返回false表示相同，返回true表示不相同
     */
    public static boolean checkVerifyCode(HttpServletRequest request){
        String verifyCodeExpected = ((String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY)).toLowerCase();
        String verifyCodeActul = (HttpServletRequestUtil.getString(request,"verifyCodeActul")).toLowerCase();
        if (verifyCodeActul == null || !verifyCodeActul.equals(verifyCodeExpected)){
            return true;
        }
        return false;
    }

    public static BitMatrix generateQRCodeStream(String content, HttpServletResponse response){
        //给响应添加头部信息，主要是告诉浏览器返回的是图片流
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("image/png");
        //设置图片的文字编码以及内边框距
        /* EncodeHintType.CHARACTER_SET：设置字符编码类型
         * EncodeHintType.ERROR_CORRECTION：设置误差校正
         * ErrorCorrectionLevel：误差校正等级，L = ~7% correction、M = ~15% correction、Q = ~25% correction、H = ~30% correction
         * 不设置时，默认为 L 等级，等级不一样，生成的图案不同，但扫描的结果是一样的
         * EncodeHintType.MARGIN：设置二维码边距，单位像素，值越小，二维码距离四周越近
         */
        Map<EncodeHintType, Object> hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix;
        try {
            //参数顺序分别为：二维码内容，编码类型（如二维码，条形码等），码的宽度，码的高度，设置参数
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
        }catch (WriterException e){
            e.printStackTrace();
            return null;
        }
        return bitMatrix;
    }
}
