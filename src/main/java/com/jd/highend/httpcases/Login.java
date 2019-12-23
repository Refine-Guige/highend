package com.jd.highend.httpcases;

import com.jd.highend.model.Department;
import com.jd.highend.utils.AESUtils;
import com.jd.highend.utils.DatabaseUtil;
import com.jd.highend.utils.PostUtils;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;


public class Login  {
    private static final String AES_KEY = "aUF3dHB3ZA==YHRD";
    public String token="";
@Test(priority = 1)
public void login(){
    String loginEncrypt=null;
    String response=null;
    String loginstr = "{\"login_name\":\"admin\",\"password\":\"Dj2018&&\",\"verifyCode\":\"\",\"loginCnt\":\"\"}";
    PostUtils postUtils=new PostUtils();
    AESUtils aesUtils=new AESUtils();
    try {
        loginEncrypt = aesUtils.aesEncrypt(loginstr, AES_KEY);
    } catch (Exception e) {
        e.printStackTrace();
    }
    System.out.println(loginEncrypt);
    try {
        response=postUtils.postMethod("http://172.25.49.142:8077/djjf-web/login/doLogin",loginEncrypt);
    } catch (IOException e) {
        e.printStackTrace();
    }
    token= JSONObject.fromObject(response).get("data").toString();
    System.out.println("token:\t"+token);


    try {
        SqlSession sqlSession = DatabaseUtil.getsqlSession();
        Department department=sqlSession.selectOne("selectByDeptId","35");
        System.out.println(department.toString());
        System.out.println(department.getDept_id());

    } catch (IOException e) {
        e.printStackTrace();
    }


}



    @Test(priority = 2,dependsOnMethods = "login")
    public void queryCustomerList(){
        String loginEncrypt=null;
        String response=null;
        String loginstr = "{\"pageNumber\":1,\"pageSize\":15,\"contact\":\"\",\"mobilephone\":\"\",\"pinCode\":\"\",\"customerType\":\"\",\"tradeFlag\":\"\",\"realNameFlag\":\"\",\"riskLevel\":\"\",\"assetCertificateFlag\":\"\",\"source\":\"\",\"relaAuth\":\"\",\"createdTimeStart\":\"\",\"createdTimeEnd\":\"\",\"total\":267}";
        PostUtils postUtils=new PostUtils();
        AESUtils aesUtils=new AESUtils();
        try {
            loginEncrypt = aesUtils.aesEncrypt(loginstr, AES_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(loginEncrypt);
        try {
            response=postUtils.postMethod("http://172.25.49.142:8077/djjf-web/customerList/v2/query",loginEncrypt,token);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
