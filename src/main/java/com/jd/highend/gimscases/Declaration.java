package com.jd.highend.gimscases;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jd.highend.utils.AESUtils;
import com.jd.highend.utils.PostUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Declaration {
    private static final String AES_KEY = "aUF3dHB3ZA==YHRD";
    public String token="";
    String filePath="E:\\05_testdata\\"+new SimpleDateFormat("yyMMdd").format(new Date())+".txt";
    String productId=null;
    String startId=null;
    String pinCode=null;
    String contact=null;
    String mobilePhone=null;
    String idNumber=null;
    @BeforeTest
    public void beforeTest() throws IOException {
        String str;
        //读取客户信息
        BufferedReader in = new BufferedReader(new FileReader(filePath));
        while ((str = in.readLine()) != null) {
            if(str.startsWith("userpin")){
                pinCode=str.split(":")[1];
            }else if(str.startsWith("name")){
                contact=str.split(":")[1];
            }else if(str.startsWith("mobile")){
                mobilePhone=str.split(":")[1];
            }else if(str.startsWith("idNumber")){
                idNumber=str.split("idNumber")[1];
            }else{
                System.out.println("没有找到这种前缀类型！");
            }
        }
    }



    @Test(priority = 1,parameters = {"url"})
    public void login(String url) throws Exception {
        String requestUrl=url+"/login/doLogin";
        String loginstr = "{\"login_name\":\"admin\",\"password\":\"Dj2018&&\",\"verifyCode\":\"\",\"loginCnt\":\"\"}";
        //对入参加密
        String loginEncrypt = AESUtils.aesEncrypt(loginstr, AES_KEY);
        System.out.println("request_url:\t"+requestUrl);
        System.out.println("request_data:\t"+loginstr);
        String response= PostUtils.postMethod(requestUrl,loginEncrypt);
        token= JSONObject.fromObject(response).get("data").toString();
        System.out.println("response_data:\t"+response);
        System.out.println("token:\t"+token);

    }


    @Test(priority = 2,parameters = {"url"})
    public void queryProductBooking (String url) throws Exception {
        String requestUrl=url+"/booking/queryProductBooking";
        String productName="临时用类固收全名";

        Map map=new HashMap();
        map.put("productName",productName);
        //将参数转换为json格式再转为string类型
        JSONObject jsonObject=JSONObject.fromObject(map);
        String requestStr=jsonObject.toString();
        System.out.println("request_url:\t"+requestUrl);
        System.out.println("request_data:\t"+requestStr);

        //对入参加密
        String loginEncrypt = AESUtils.aesEncrypt(requestStr, AES_KEY);
        String response=PostUtils.postMethod(requestUrl,loginEncrypt,token);
        System.out.println("response_data:\t"+response);
        //断言
        JSONObject responseJson=  JSONObject.fromObject(response);
        Assert.assertEquals(responseJson.get("code").toString(),"200");
        Assert.assertEquals(responseJson.get("succ").toString(),"true");
        Assert.assertEquals(responseJson.get("message").toString(),"OK");
        //返回结果参数化
        JSONObject data= (JSONObject) responseJson.get("data");
        JSONArray records= JSONArray.fromObject(data.get("records"));
        JSONObject recordsJSONObject=records.getJSONObject(0);
        //获取产品id productId
        productId=recordsJSONObject.getString("productId");
        //获取产品startId
        JSONObject bookingStart=recordsJSONObject.getJSONObject("bookingStart");
        startId=bookingStart.getString("id");


    }


    @Test(priority = 3,parameters = {"url"})
    public void getProduct(String url) throws Exception {
        String requestUrl=url+"/product/get";
        Map map=new HashMap();
        map.put("productId",productId);
        //将参数转换为json格式再转为string类型
        JSONObject jsonObject=JSONObject.fromObject(map);
        String requestStr=jsonObject.toString();
        System.out.println("request_url:\t"+requestUrl);
        System.out.println("request_data:\t"+requestStr);

        //对入参加密
        String loginEncrypt = AESUtils.aesEncrypt(requestStr, AES_KEY);
        String response=PostUtils.postMethod(requestUrl,loginEncrypt,token);
        System.out.println("response_data:\t"+response);
        //断言
        JSONObject responseJson=  JSONObject.fromObject(response);
        Assert.assertEquals(responseJson.get("code").toString(),"200");
        Assert.assertEquals(responseJson.get("succ").toString(),"true");
        Assert.assertEquals(responseJson.get("message").toString(),"OK");

    }

    @Test(priority = 4,parameters = {"url"})
    public void getBookingStart(String url) throws Exception {
        String requestUrl=url+"/distributeManage/getBookingStart";
        Map map=new HashMap();
        map.put("productId",productId);
        //将参数转换为json格式再转为string类型
        JSONObject jsonObject=JSONObject.fromObject(map);
        String requestStr=jsonObject.toString();
        System.out.println("request_url:\t"+requestUrl);
        System.out.println("request_data:\t"+requestStr);

        //对入参加密
        String loginEncrypt = AESUtils.aesEncrypt(requestStr, AES_KEY);
        String response=PostUtils.postMethod(requestUrl,loginEncrypt,token);
        System.out.println("response_data:\t"+response);
        //断言
        JSONObject responseJson=  JSONObject.fromObject(response);
        Assert.assertEquals(responseJson.get("code").toString(),"200");
        Assert.assertEquals(responseJson.get("succ").toString(),"true");
        Assert.assertEquals(responseJson.get("message").toString(),"成功");
    }

    @Test(priority = 5,parameters = {"url"})
    public void customerListQuery(String url) throws Exception {
        String requestUrl=url+"/customerList/v2/query";
        Map map=new HashMap();
        map.put("customerId","");
        map.put("customerType","");
        map.put("mobilephone","");
        map.put("contact","");
        //将参数转换为json格式再转为string类型
        JSONObject jsonObject=JSONObject.fromObject(map);
        String requestStr=jsonObject.toString();
        System.out.println("request_url:\t"+requestUrl);
        System.out.println("request_data:\t"+requestStr);

        //对入参加密
        String loginEncrypt = AESUtils.aesEncrypt(requestStr, AES_KEY);
        String response=PostUtils.postMethod(requestUrl,loginEncrypt,token);
        System.out.println("response_data:\t"+response);
        //断言
        JSONObject responseJson=  JSONObject.fromObject(response);
        Assert.assertEquals(responseJson.get("code").toString(),"200");
        Assert.assertEquals(responseJson.get("succ").toString(),"true");
        Assert.assertEquals(responseJson.get("message").toString(),"成功");
    }

    @Test(priority = 6,parameters = {"url"})
    public void getCustomerComplianceInfo(String url) throws Exception {
        String requestUrl=url+"/customerOverview/getCustomerComplianceInfo";
        Map map=new HashMap();
        map.put("pinCode",pinCode);
        //将参数转换为json格式再转为string类型
        JSONObject jsonObject=JSONObject.fromObject(map);
        String requestStr=jsonObject.toString();
        System.out.println("request_url:\t"+requestUrl);
        System.out.println("request_data:\t"+requestStr);

        //对入参加密
        String loginEncrypt = AESUtils.aesEncrypt(requestStr, AES_KEY);
        String response=PostUtils.postMethod(requestUrl,loginEncrypt,token);
        System.out.println("response_data:\t"+response);
        //断言
        JSONObject responseJson=  JSONObject.fromObject(response);
        Assert.assertEquals(responseJson.get("code").toString(),"200");
        Assert.assertEquals(responseJson.get("succ").toString(),"true");
        Assert.assertEquals(responseJson.get("message").toString(),"OK");

    }


}
