package com.jd.highend.jsfcases;

import com.jd.dpms.dbs.api.enums.EmServiceErrorCode;
import com.jd.dpms.dbs.api.pojo.CityInfo;
import com.jd.dpms.dbs.api.response.Response;
import com.jd.dpms.dbs.api.result.CityInfoResult;
import com.jd.dpms.dbs.api.service.ICityService;
import com.jd.highend.listener.HighendTestListener;
import com.jd.jr.bx.common.res.GenericResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;


@SpringBootTest
//@Listeners(value = {HighendTestListener.class})
public class TestCity extends AbstractTestNGSpringContextTests {

    @Autowired
    private ICityService iCityService;

    @DataProvider(name = "part")
    public Object[][] part() {
        return new Object[][]{
                {"getCityInfo第一个测试用例",1}, {"getCityInfo第一个测试用例",2}, {"getCityInfo第一个测试用例",3}, {"getCityInfo第一个测试用例",1000}, {"getCityInfo第一个测试用例",55}
        };
    }

    @BeforeClass
    public void setup() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext;
        System.out.println("BeforeClass");
    }

    @Test(testName = "入参",groups = "a", dataProvider = "part", threadPoolSize = 1, invocationCount = 1, timeOut = 100000)
    public void getCityInfo(String casename, Integer cityId) {
        CityInfo cityInfo = new CityInfo();
        cityInfo.setId(cityId);
        GenericResult<CityInfoResult> cityById = iCityService.findCityById(cityInfo);
//        System.out.println(cityById.getValue().getChineseName());
        System.out.println(cityById);
        System.out.println(cityById.getCode());
        System.out.println(cityById.getValue().getId());
        Assert.assertNotNull(cityInfo);
        Assert.assertEquals(cityById.getCode(), EmServiceErrorCode.SUCCESS.getCode());
        Assert.assertEquals(cityById.getValue().getId(), cityId);
    }


    @Test(testName = "入参",groups = "b", dataProvider = "part", threadPoolSize = 1, invocationCount = 1, timeOut = 100000)
    public void getRealtyInfo(Integer cityId) {
        CityInfo cityInfo = new CityInfo();
        cityInfo.setId(cityId);
        Response<Integer> realtyByCityId = iCityService.findRealtyByCityId(cityInfo);
//        System.out.println(realtyByCityId.getDatas());
        Assert.assertNotNull(cityInfo);
    }

    @BeforeGroups(value = "a")
    public void m(){
        System.out.println("a");
    }

    @BeforeSuite
    public void aa() {
        System.out.println("BeforeSuite");
    }

    @AfterClass
    public void cc() {
        System.out.println("AfterClass");
    }

    @AfterSuite
    public void a() {
        System.out.println("AfterSuite");
    }

    @BeforeMethod
    public void b() {
        System.out.println("BeforeMethod");
    }

    @AfterMethod
    public void c() {
        System.out.println("AfterMethod");
    }

    @BeforeTest
    public void d() {
        System.out.println("BeforeTest");
    }

    @AfterTest
    public void f() {
        System.out.println("AfterTest");
    }
}

