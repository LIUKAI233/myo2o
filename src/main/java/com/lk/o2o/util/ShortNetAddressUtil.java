package com.lk.o2o.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 传入比较长的URL通过百度的接口，转换成短的URL    废弃
 */
public class ShortNetAddressUtil {
    private static Logger log = LoggerFactory.getLogger(ShortNetAddressUtil.class);

    /**
     * 根据传入的url，通过访问百度短视频的接口，将其转换成短的URL
     * @param originURL 需要转换的URL
     * @return 处理结果
     */
    public static String getShortURL(String originURL){
        String tinyUrl = null;
        try {
            //指定百度短视频的接口
            URL url = new URL("");
            //建立连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置连接的参数
            //使用连接进行输出
            connection.setDoOutput(true);
            //使用连接进行输入
            connection.setDoInput(true);
            //不使用缓存
            connection.setUseCaches(false);
            //设置连接超时时间为30秒
            int TIMEOUT = 30 * 1000;
            connection.setConnectTimeout(TIMEOUT);
            //设置请求模式为POST
            connection.setRequestMethod("POST");
            //设置POST信息，这里为传入的原始URL
            String postData = URLEncoder.encode(originURL, "utf-8");
            //输出原始的url
            connection.getOutputStream().write(("url="+postData).getBytes());
            //连接百度短视频接口
            connection.connect();
            //获取返回的字符串
            String responseStr = getResponseStr(connection);
            log.info("response string :"+responseStr);
            //在字符串里获取tinyurl，即短链接
            tinyUrl = getValueByKey(responseStr);
            log.info("tinyurl:"+tinyUrl);
            //关闭链接
            connection.disconnect();
        }catch (IOException e){
            log.error("getShortURL erroe:"+e.toString());
        }
        return tinyUrl;
    }

    /**
     * JSON 依据传入的key获取对应的值
     * @param responseStr 字符串
     * @return 键对应的值
     */
    private static String getValueByKey(String responseStr) {
        ObjectMapper mapper = new ObjectMapper();
        //定义JSON节点
        JsonNode node;
        String targetValue = null;
        try {
            //把字符串转成JSON对象
            node = mapper.readTree(responseStr);
            //依据key从JSON对象中获取对应的值
            targetValue = node.get("tinyUrl").asText();
        } catch (IOException e){
            log.error("getValueByKey error:"+e.toString());
        }
        return targetValue;
    }

    /**
     * 通过HttpConnecting 获取返回的字符串
     * @param connection 连接
     * @return 返回的字符串
     */
    private static String getResponseStr(HttpURLConnection connection) throws IOException {
        StringBuffer result = new StringBuffer();
        //从连接中获取http状态码
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK){
            //如果返回的状态码是OK的，那么取出连接的输入流
            InputStream in = connection.getInputStream();
            String ENCODING = "UTF-8";
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, ENCODING));
            String inputLine;
            while ((inputLine = reader.readLine()) != null){
                //将消息逐行读入结果中
                result.append(inputLine);
            }
        }
        //将结果转换成String并返回
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        String shortURL = getShortURL("https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login");
        System.out.println(shortURL);
    }
}
