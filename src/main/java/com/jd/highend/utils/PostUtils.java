package com.jd.highend.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostUtils {
    public static String postMethod(String urlStr,String requestStr) throws IOException {
        URL url=new URL(urlStr);
        HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();


        httpURLConnection.setRequestProperty("Cookie", "JSESSIONID=FPHZQyVhY1T7sSxxwEp7fOG1QKuGky-Lvv7DgepX");

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setRequestProperty("Connection","keep-alive");
        httpURLConnection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
        httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8"); // 设置发送数据的格式
        httpURLConnection.connect();

        OutputStream outputStream = httpURLConnection.getOutputStream();
        PrintWriter printWriter=new PrintWriter(outputStream);
        printWriter.write(requestStr);
        printWriter.flush();
        printWriter.close();


        int responseCode=httpURLConnection.getResponseCode();
        InputStream inputStream= null;
        if(responseCode==200){
            inputStream=httpURLConnection.getInputStream();
        }else {
            inputStream=httpURLConnection.getErrorStream();
        }


        BufferedInputStream bis = new BufferedInputStream(inputStream);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len;
        byte[] arr = new byte[1024];
        while((len=bis.read(arr))!= -1){
            bos.write(arr,0,len);
            bos.flush();
        }
        bos.close();
        String postResponse=bos.toString();
        System.out.println("返回结果:\t"+postResponse);
        return postResponse;

    }

    public static  String postMethod(String urlStr,String requestStr,String token) throws IOException {
        URL url=new URL(urlStr);
        HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();


        httpURLConnection.setRequestProperty("Cookie", "JSESSIONID=FPHZQyVhY1T7sSxxwEp7fOG1QKuGky-Lvv7DgepX");

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setRequestProperty("Connection","keep-alive");
        httpURLConnection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
        httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8"); // 设置发送数据的格式
        httpURLConnection.setRequestProperty("token", token);
        httpURLConnection.connect();

        OutputStream outputStream = httpURLConnection.getOutputStream();
        PrintWriter printWriter=new PrintWriter(outputStream);
        printWriter.write(requestStr);
        printWriter.flush();
        printWriter.close();


        int responseCode=httpURLConnection.getResponseCode();
        InputStream inputStream= null;
        if(responseCode==200){
            inputStream=httpURLConnection.getInputStream();
        }else {
            inputStream=httpURLConnection.getErrorStream();
        }


        BufferedInputStream bis = new BufferedInputStream(inputStream);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len;
        byte[] arr = new byte[1024];
        while((len=bis.read(arr))!= -1){
            bos.write(arr,0,len);
            bos.flush();
        }
        bos.close();
        String postResponse=bos.toString();
        //System.out.println("返回结果:\t"+postResponse);
        return postResponse;

    }
}
