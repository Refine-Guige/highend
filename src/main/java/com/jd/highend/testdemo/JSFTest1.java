package com.jd.highend.testdemo;

import com.dj.gims.api.product.IGimsProductService;
import com.dj.gims.api.product.resp.BenifitDataVo;
import com.dj.gims.api.product.result.GimsProductBasicResult;
import com.dj.gims.api.trade.IGimsTradeService;
import com.dj.gims.api.trade.resp.HoldAndIncomeDto;
import com.dongjia.api.module.vo.PlannerVo;
import com.dongjia.api.module.vo.WeChatVo;
import com.jd.jr.bx.common.res.GenericResult;
import com.jd.jr.fin.item.base.export.res.MerchantVo;
import com.jd.jr.fin.item.base.export.res.NetValueVo;
import com.jd.jr.jim.vo.ArticleVO;
import com.jd.pfund.acc.api.pojo.GdAccUserHoldenCard;
import com.jd.pfund.api.dm.DMMerchantService;
import com.jd.pfund.api.dm.DMProductService;
import com.jd.pfund.api.dongrich.DongrichService;
import com.jd.pfund.response.PageData;
import com.jd.pfund.response.Response;
import com.jd.pfund.response.dmproduct.result.*;
import com.jd.pfund.response.trade.result.ValidateInvestorIsCanPurchaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import static com.jd.pfund.enums.EmRequestSource.WEB;
    import com.jd.pfund.acc.api.service.IGdAccAllInsideService;

@SpringBootTest
public class JSFTest1  extends AbstractTestNGSpringContextTests {
    @Autowired
    private DongrichService dongrichService;
    @Autowired
    private DMMerchantService dmMerchantService;
    @Autowired
    private DMProductService dmProductService;
    @Autowired
    IGdAccAllInsideService iGdAccAllInsideService;
    @Autowired
    private IGimsProductService iGimsProductService;
    @Autowired
    private IGimsTradeService iGimsTradeService;

    private static ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("config/spring-jd.xml");
    private static IGimsTradeService iGimsTradeService1=null;

    @Test
    public void getActivityTemplateData() {

    }

        @Test
    public void queryUserHoldAndIncome(){
        iGimsTradeService1= (IGimsTradeService) appContext.getBean("IGimsTradeService");
        com.dj.gims.api.trade.resp.Response<HoldAndIncomeDto> bfi;
        bfi = iGimsTradeService1.queryUserHoldAndIncome("v1","smywxt","jd_user33333302",0) ;

        System.out.println(bfi.getCode()+"\n"+bfi.getData().getTotalAmount()+"\n"+bfi.getMessage().toString());

    }

    @Test
    public void getGdAccUserHoldenCard(){
        GdAccUserHoldenCard gdAccUserHoldenCard=new GdAccUserHoldenCard();
        gdAccUserHoldenCard.setUserPin("gdqs001");
        gdAccUserHoldenCard.setCardId("scrt_xjklcj");
        gdAccUserHoldenCard.setProductId("900009");
        GenericResult<GdAccUserHoldenCard> uui = iGdAccAllInsideService.getGdAccUserHoldenCard(gdAccUserHoldenCard);
        System.out.println(uui.getValue().getProductId()+"\n"+uui.getValue().getRedeemableVol());
    }




    //ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("config/pfund-jsf.xml");
    //DongrichService dongrichService=(DongrichService) appContext.getBean("DongrichService");

    String  ss="{\"userPin\":\"jd_user33333302\"}";
    @Test(invocationCount=1)
    public void jointQuery(){
        String josnParams="[\"\",]";
       com.jd.pfund.response.Response<ValidateInvestorIsCanPurchaseResult> jq;
        jq = dongrichService.jointQuery(1,1,WEB,"{\"userPin\":\"jd_user33333302\"}");
        jq.getDatas().getDirectDesc();

        System.out.println(jq.getDatas().getJump());
        System.out.println(jq.getStatus());
        System.out.println(jq.getErrorMessage());
        System.out.println(jq.getErrorCode());
    }

    @Test
    public void getBenifitInfo(){

        Integer a=1;

        com.jd.pfund.response.Response<List<BenifitDataVo>> bfi;
       bfi = dongrichService.getBenifitInfo(1,1,WEB,"20001001000090") ;
       System.out.println(bfi.getDatas().get(1).getName());

    }
    @Test
    public void optionPlanner(){
        String tt="ee";

       com.jd.pfund.response.Response<Boolean> s= dongrichService.optionPlanner(1,1,WEB,"{\"userPin\":\"2344\",\"plannerId\":\"999399\"}");

       System.out.println(s.toString()+"\n"+s.getDatas().toString());
    }

    @Test
    public void weChatShare(){
        com.jd.pfund.response.Response<WeChatVo> s=dongrichService.weChatShare(1,1,WEB,"{\"url\":\"https://mrich.jd.com\"}");
        System.out.println(s.toString());
   }




    @Test
    public void getArticleById(){
        Response<ArticleVO> s=dongrichService.getArticleById(1,1,WEB, 678);
        System.out.println(s.getDatas().getId());
        System.out.println(s.getErrorCode());
    }

    @Test
    public void getPerformanceListAll(){
        Response<Object> s=dongrichService.getPerformanceListAll(1,1,WEB,"20001001000090" );
        System.out.println(s.getDatas());
    }

    @Test
    public void findAllPlannerByPin(){
        Response<List<PlannerVo>> s=dongrichService.findAllPlannerByPin(1,1,WEB,"{\"userPin\":\"\"}" );
        System.out.println(s.toString());
    }




    @Test
    public void isViewBindingPlanner(){
        Response<Boolean> s=dongrichService.isViewBindingPlanner(1,1,WEB,"{\"userPin\":\"\"" );
        System.out.println(s.toString()+"77777"+s.getErrorCode());
    }

    @Test
    public void getMerchantById(){
        Response<MerchantVo> s=dmMerchantService.getMerchantById(1,1,WEB,"" );
        System.out.println(s.getDatas());
    }


    @Test
    public void getProduct(){
        String ttttt="{\"skuId\":\"20001001000090\"}";

        Response<GdDMProductBase> s=dmProductService.getProduct(1,1,WEB,"20021001000003");
        System.out.println(s.getDatas().toString());
    }

    @Test
    public void getLastNav(){
        Response<NetValueVo> s=dmProductService.getLastNav(1,1,WEB,"20021001000003" );
        System.out.println(s.getStatus().toString()+"\n"+s.getDatas().toString());
    }


    @Test
    public void listRecommend(){
        Response<List<Map<String, Object>>> s=dmProductService.listRecommend( 1,1,WEB);
        System.out.println(s.getErrorCode());
    }
    @Test
    public void getSimpleInfo(){
        Response<GdDMProductSimple> s=dmProductService.getSimpleInfo(1,1,WEB,"20021001000003" );
        System.out.println(s.toString());
        System.out.println(s.getErrorCode());
    }
    @Test
    public void listPageByType(){
        Response<PageData<List<Map<String, Object>>>> s=dmProductService.listPageByType(1,1,WEB,"{\"productType\":\"2\",\"pageNo\":\"1\",\"pageSize\":\"10\"}" );
        System.out.println(s.toString());
        System.out.println(s.getErrorCode());
    }
    @Test
    public void getControlStatus(){
        String ii="{\"skuId\":\"20001001000090\",\"page\":\"detail\",\"userPin\":\"jd_user33333302\"}";
        Response<List<GdDMStatusControl>> s=dmProductService.getControlStatus(1,1,WEB,ii );
        System.out.println(s.toString());
        System.out.println(s.getErrorCode());
    }
    @Test
    public void isWorkTime(){
        Response<Boolean> s=dmProductService.isWorkTime(1,1,WEB,"20001001000090" );
        System.out.println(s.toString());
        System.out.println(s.getErrorCode());
    }
    @Test
    public void getProductFAQ(){
        Response<List<GdDMProductFAQ>> s=dmProductService.getProductFAQ(1,1,WEB,"20001001000090" );
        System.out.println(s.toString());
        System.out.println(s.getErrorCode());
    }
    @Test
    public void queryFundManager(){
        Response<List<GDDMFundManager>> s=dmProductService.queryFundManager(1,1,WEB,"20001001000090" );
        System.out.println(s.toString());
        System.out.println(s.getErrorCode());
    }
    @Test
    public void getProductReport(){
        Response<List<GdDMProductContract>> s=dmProductService.getProductReport(1,1,WEB,"20001001000090" );
        System.out.println(s.toString());
        System.out.println(s.getErrorCode());
    }
    @Test
    public void queryProductFee(){
        Response<GdDMProductFee> s=dmProductService.queryProductFee(1,1,WEB,"20001001000090" );
        System.out.println(s.toString());
        System.out.println(s.getErrorCode());
    }
    @Test
    public void getThirteen(){
        Response<List<GdDMProductContract>> s=dmProductService.getThirteen(1,1,WEB,"20001001000090" );
        System.out.println(s.toString());
        System.out.println(s.getErrorCode());
    }
    @Test
    public void getServiceInfo(){
        Response<GdDMServiceInfo> s=dmProductService.getServiceInfo(1,1,WEB,"20001001000090" );
        System.out.println(s.toString());
    }
    @Test
    public void getProductAffiche(){
        Response<List<GdDMProductContract>> s=dmProductService.getProductAffiche(1,1,WEB,"20001001000090" );
        System.out.println(s.toString());
    }


    @Test
    public void queryProductDividend(){
        Response<List<GdDMProductDividend>> s=dmProductService.queryProductDividend(1,1,WEB,"20001001000090" );
        System.out.println(s.toString());
    }

    @Test
    public void getPreferenceProduct(){
        Response<Map<String, Object>> s=dmProductService.getPreferenceProduct(1,1,WEB,"20001001000090" );
        System.out.println(s.toString());
    }

    @Test
    public void getProductContract(){
        Response<List<GdDMProductContract>> s=dmProductService.getProductContract(1,1,WEB,"20001001000090" );
        System.out.println(s.toString());
    }

    @Test
    public void queryProductManager(){
        Response<GdDMProductManager> s=dmProductService.queryProductManager(1,1,WEB,"20001001000090" );
        System.out.println(s.getErrorCode());
    }
}

