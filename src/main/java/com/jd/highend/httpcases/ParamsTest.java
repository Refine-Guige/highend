package com.jd.highend.httpcases;

import org.testng.annotations.Test;

public class ParamsTest {
    @Test(parameters = {"url"})
    public void test1(String url){

        System.out.println(url);

    }

}
