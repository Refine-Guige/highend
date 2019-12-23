package com.jd.highend.testdemo;

import com.dj.gims.api.product.IGimsProductService;
import com.dj.gims.api.product.result.GimsProductBasicResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class IGimsProductServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private IGimsProductService iGimsProductService;




    @Test
    public void saveProductNetValue(){
        String inputParam="{\"bizName\":\"私募业务\",\"bizType\":\"20\",\"fundCode\":\"909319\",\"merchantId\":\"20004\",\"merchantName\":\"中信证券股份有限公司\",\"netValueDate\":1568908800000,\"productName\":\"阳光私募\",\"productType\":\"3\",\"shopId\":\"20004001\",\"shopName\":\"中信证券-财富\",\"skuId\":\"99000003\",\"skuName\":\"中信证券红利价值增强1号\",\"status\":4,\"totalNetValue\":1.2385,\"unitNetValue\":1.1885}";
        String inputParam1="{\"netValueDate\":\"2019-09-24\",\"skuId\":\"20021001000003\",\"totalNetValue\":1.8888,\"unitNetValue\":1.1111}";

        String inputParam2="{\"bizName\":\"\",\"bizType\":\"\",\"fundCode\":\"\",\"merchantId\":\"\",\"merchantName\":\"\",\"netValueDate\":\"2019-09-24\",\"productName\":\"\",\"productType\":\"\",\"shopId\":\"\",\"shopName\":\"\",\"skuId\":\"20001001000090\",\"skuName\":\"\",\"status\":\"\",\"totalNetValue\":1.3333,\"unitNetValue\":1.2222}";

        GimsProductBasicResult bfi;
        bfi =iGimsProductService.saveProductNetValue(inputParam1) ;
        System.out.println(bfi.toString());
        System.out.println(bfi.getData().toString());

    }



}
