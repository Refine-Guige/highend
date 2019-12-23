package com.jd.highend.gimscases;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.jd.highend.model.Department;
import com.jd.highend.utils.AESUtils;
import com.jd.highend.utils.DatabaseUtil;
import com.jd.highend.utils.PostUtils;
import com.jd.highend.utils.TxtFileUntils;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AddConsumer {

    private static final String AES_KEY = "aUF3dHB3ZA==YHRD";
    public String token="";
    String filePath="E:\\05_testdata\\"+new SimpleDateFormat("yyMMdd").format(new Date())+".txt";
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
    public void addCustomer(String url) throws Exception {
        String str;
        String pinCode = null,contact=null,mobilePhone=null,idNumber=null;
        String requestUrl=url+"/customerList/v2/addCustomer";
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

        Map map=new HashMap();
        map.put("pinCode",pinCode);
        map.put("contact",contact);
        map.put("mobilePhone",mobilePhone);
        map.put("timeless","0");
        map.put("source","3");
        //将参数转换为json格式再转为string类型
        JSONObject jsonObject=JSONObject.fromObject(map);
        String requestStr=jsonObject.toString();
        System.out.println("request_url:\t"+requestUrl);
        System.out.println("request_data:\t"+requestStr);

        //对入参加密
        String loginEncrypt = AESUtils.aesEncrypt(requestStr, AES_KEY);
        String response=PostUtils.postMethod(requestUrl,loginEncrypt,token);
        System.out.println("response_data:\t"+response);

        Gson gson = new Gson();
        Map resMap = gson.fromJson(response, new TypeToken<Map<String, Object>>() {}.getType());
        Assert.assertEquals(200.00,resMap.get("code"));
        Assert.assertEquals(true,resMap.get("succ"));

    }




}
