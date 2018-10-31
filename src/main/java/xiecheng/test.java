//package xiecheng;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.regex.Pattern;
//
///**
// * Created by zhuhaoju on 2018/2/28.
// */
//public class test {
//
//
////    max(planeaddr_p2,planeaddr_p3)=空
////    max(planeaddr_p2,planeaddr_p3)<0.6905
////            0.6905<=max(planeaddr_p2,planeaddr_p3)<0.83975
////            0.83975<=max(planeaddr_p2,planeaddr_p3)<0.9298
////            0.9298<=max(planeaddr_p2,planeaddr_p3)<0.9631
////            0.9631<=max(planeaddr_p2,planeaddr_p3)
//
//    @Test
//    public void appLikeMissingTest()  {
//        Assert.assertEquals(FeatureEnumType.AppLikeMissing.IS_MISS.getScore(),FeatureEnumType.AppLikeMissing.getResult(0),0.000001);
//        Assert.assertEquals(FeatureEnumType.AppLikeMissing.IS_NOT_MISS.getScore(),FeatureEnumType.AppLikeMissing.getResult(1),0.000001);
//        Assert.assertEquals(FeatureEnumType.AppLikeMissing.IS_MISS.getScore(),FeatureEnumType.AppLikeMissing.getResult(null),0.000001);
//    }
//
//    @Test
//    public void outPlanTest() {
//        Assert.assertEquals(FeatureEnumType.OutPlan.OUT_PLAN_SECTION_EMPTY.getScore(),FeatureEnumType.OutPlan.getResult(-1.0),0.000001);
//        Assert.assertEquals(FeatureEnumType.OutPlan.OUT_PLAN_SECTION_EMPTY.getScore(),FeatureEnumType.OutPlan.getResult(null),0.000001);
//
//        Assert.assertEquals(FeatureEnumType.OutPlan.OUT_PLAN_SECTION_1.getScore(),FeatureEnumType.OutPlan.getResult(0.058),0.000001);
//        Assert.assertEquals(FeatureEnumType.OutPlan.OUT_PLAN_SECTION_2.getScore(),FeatureEnumType.OutPlan.getResult(0.059),0.000001);
//        Assert.assertEquals(FeatureEnumType.OutPlan.OUT_PLAN_SECTION_7.getScore(),FeatureEnumType.OutPlan.getResult(0.3355),0.000001);
//    }
//
////    paywayfir in ("钱包","储蓄卡","网银","外币兑换微信支付")
////    paywayfir in ("ApplePay","百度钱包","支付宝","微信支付","QQ钱包","银联手机支付")
////    paywayfir in ("担保","信用支付","信用卡","票券")
//
//
//    @Test
//    public void payFirstTest()  {
//        Assert.assertEquals(0.0,FeatureEnumType.PayFirst.getResult(""),0.000001);
//        Assert.assertEquals(0.0,FeatureEnumType.PayFirst.getResult(null),0.000001);
//        Assert.assertEquals(0.0,FeatureEnumType.PayFirst.getResult("百度小钱包:0.0"),0.000001);
//
//        Assert.assertEquals(FeatureEnumType.PayFirst.PAY_METHOD_1.getScore(),FeatureEnumType.PayFirst.getResult("钱包:0.0"),0.000001);
//        Assert.assertEquals(FeatureEnumType.PayFirst.PAY_METHOD_2.getScore(),FeatureEnumType.PayFirst.getResult("百度钱包:0.0"),0.000001);
//    }
//
//    //    paywaysec not in ("ApplePay","百度钱包","支付宝","微信支付","QQ钱包","银联手机支付","担保","信用支付","信用卡","票券","外币兑换微信支付")
////    paywaysec in ("ApplePay","百度钱包","支付宝","微信支付","QQ钱包","银联手机支付")
////    paywaysec in ("担保","信用支付","信用卡","票券")
//
//    @Test
//    public void paySecondTest() {
//        Assert.assertEquals(0.0,FeatureEnumType.PaySecond.getResult(""),0.000001);
//        Assert.assertEquals(0.0,FeatureEnumType.PaySecond.getResult(null),0.000001);
//
//        Assert.assertEquals(FeatureEnumType.PaySecond.PAY_METHOD_1.getScore(), FeatureEnumType.PaySecond.getResult("钱包:123"), 0.000001);
//        Assert.assertEquals(FeatureEnumType.PaySecond.PAY_METHOD_2.getScore(), FeatureEnumType.PaySecond.getResult("百度钱包:23"), 0.000001);
//        Assert.assertEquals(FeatureEnumType.PaySecond.PAY_METHOD_3.getScore(), FeatureEnumType.PaySecond.getResult("担保:23"), 0.000001);
//    }
//
//    //    paywaythr in ("钱包","储蓄卡","网银")
////    paywaythr in ("ApplePay","百度钱包","支付宝","微信支付","QQ钱包","银联手机支付")
////    paywaythr not in ("外币兑换微信支付","钱包","储蓄卡","网银","ApplePay","百度钱包","支付宝","微信支付","QQ钱包","银联手机支付")
//
//    @Test
//    public void payThirdTest() {
//        Assert.assertEquals(0.0,FeatureEnumType.PayThird.getResult(""),0.000001);
//        Assert.assertEquals(0.0,FeatureEnumType.PayThird.getResult(null),0.000001);
//
//        Assert.assertEquals(FeatureEnumType.PayThird.PAY_METHOD_1.getScore(), FeatureEnumType.PayThird.getResult("钱包:123"), 0.000001);
//        Assert.assertEquals(FeatureEnumType.PayThird.PAY_METHOD_2.getScore(), FeatureEnumType.PayThird.getResult("百度钱包:23"), 0.000001);
//        Assert.assertEquals(FeatureEnumType.PayThird.PAY_METHOD_3.getScore(), FeatureEnumType.PayThird.getResult("百度小钱包:23"), 0.000001);
//    }
//
//    @Test
//    public void planeAddrHighMaxTest(){
//        Assert.assertEquals(FeatureEnumType.PlaneAddrHighMax.PLANE_ADDR_HIGH_SECTION_EMPTY.getScore(),FeatureEnumType.PlaneAddrHighMax.getResult(new Double[]{null,null}),0.000001);
//        Assert.assertEquals(FeatureEnumType.PlaneAddrHighMax.PLANE_ADDR_HIGH_SECTION_EMPTY.getScore(),FeatureEnumType.PlaneAddrHighMax.getResult(new Double[]{}),0.000001);
//
//        Assert.assertEquals(FeatureEnumType.PlaneAddrHighMax.PLANE_ADDR_HIGH_SECTION_1.getScore(),FeatureEnumType.PlaneAddrHighMax.getResult(new Double[]{0.6904}),0.000001);
//        Assert.assertEquals(FeatureEnumType.PlaneAddrHighMax.PLANE_ADDR_HIGH_SECTION_2.getScore(),FeatureEnumType.PlaneAddrHighMax.getResult(new Double[]{0.6905,0.6904}),0.000001);
//        Assert.assertEquals(FeatureEnumType.PlaneAddrHighMax.PLANE_ADDR_HIGH_SECTION_5.getScore(),FeatureEnumType.PlaneAddrHighMax.getResult(new Double[]{0.9631,0.6904}),0.000001);
//        Assert.assertEquals(FeatureEnumType.PlaneAddrHighMax.PLANE_ADDR_HIGH_SECTION_2.getScore(),FeatureEnumType.PlaneAddrHighMax.getResult(new Double[]{0.6905,0.6906}),0.000001);
//        Assert.assertEquals(FeatureEnumType.PlaneAddrHighMax.PLANE_ADDR_HIGH_SECTION_1.getScore(),FeatureEnumType.PlaneAddrHighMax.getResult(new Double[]{-1.0,-1.0}),0.000001);
//    }
//
////    max(trainseat_p2,trainseat_p4,trainseat_p6,trainseat_p7)=空
////    max(trainseat_p2,trainseat_p4,trainseat_p6,trainseat_p7)<0.4035
////            0.4035<=max(trainseat_p2,trainseat_p4,trainseat_p6,trainseat_p7)<0.6021
////            0.6021<=max(trainseat_p2,trainseat_p4,trainseat_p6,trainseat_p7)<0.7633
////            0.7633<=max(trainseat_p2,trainseat_p4,trainseat_p6,trainseat_p7)
//
//    @Test
//    public void trainHighMaxTest(){
//        Assert.assertEquals(FeatureEnumType.TrainHighMax.TRAIN_HIGH_SECTION_EMPTY.getScore(),FeatureEnumType.TrainHighMax.getResult(new Double[]{null,null}),0.000001);
//        Assert.assertEquals(FeatureEnumType.TrainHighMax.TRAIN_HIGH_SECTION_EMPTY.getScore(),FeatureEnumType.TrainHighMax.getResult(new Double[]{}),0.000001);
//
//        Assert.assertEquals(FeatureEnumType.TrainHighMax.TRAIN_HIGH_SECTION_1.getScore(),FeatureEnumType.TrainHighMax.getResult(new Double[]{0.4033,0.4034,null}),0.000001);
//        Assert.assertEquals(FeatureEnumType.TrainHighMax.TRAIN_HIGH_SECTION_2.getScore(),FeatureEnumType.TrainHighMax.getResult(new Double[]{0.4035,0.4034,null}),0.000001);
//        Assert.assertEquals(FeatureEnumType.TrainHighMax.TRAIN_HIGH_SECTION_EMPTY.getScore(),FeatureEnumType.TrainHighMax.getResult(new Double[]{}),0.000001);
//    }
//
////    trainseat_p3=空
////    trainseat_p3<0.0686
////            0.0686<=trainseat_p3<0.1165
////            0.1165<=trainseat_p3<0.1835
////            0.1835<=trainseat_p3<0.32375
////            0.32375<=trainseat_p3
//
//    @Test
//    public void trainSeatP3Test(){
//        Assert.assertEquals(FeatureEnumType.TrainSeatP3.TRAINSEAT_P3_SECTION_EMPTY.getScore(),FeatureEnumType.TrainSeatP3.getResult(null),0.000001);
//
//        Assert.assertEquals(FeatureEnumType.TrainSeatP3.TRAINSEAT_P3_SECTION_1.getScore(),FeatureEnumType.TrainSeatP3.getResult(-1.0),0.000001);
//        Assert.assertEquals(FeatureEnumType.TrainSeatP3.TRAINSEAT_P3_SECTION_2.getScore(),FeatureEnumType.TrainSeatP3.getResult(0.0686),0.000001);
//        Assert.assertEquals(FeatureEnumType.TrainSeatP3.TRAINSEAT_P3_SECTION_5.getScore(),FeatureEnumType.TrainSeatP3.getResult(0.32375),0.000001);
//    }
//
////    trainseat_p5=空
////    trainseat_p5<0.05335
////            0.05335<=trainseat_p5<0.1188
////            0.1188<=trainseat_p5<0.17445
////            0.17445<=trainseat_p5
//
//    @Test
//    public void trainSeatP5Test(){
//        Assert.assertEquals(FeatureEnumType.TrainSeatP5.TRAINSEAT_P5_SECTION_EMPTY.getScore(),FeatureEnumType.TrainSeatP5.getResult(null),0.000001);
//
//        Assert.assertEquals(FeatureEnumType.TrainSeatP5.TRAINSEAT_P5_SECTION_1.getScore(),FeatureEnumType.TrainSeatP5.getResult(-1.0),0.000001);
//        Assert.assertEquals(FeatureEnumType.TrainSeatP5.TRAINSEAT_P5_SECTION_2.getScore(),FeatureEnumType.TrainSeatP5.getResult(0.05335),0.000001);
//        Assert.assertEquals(FeatureEnumType.TrainSeatP5.TRAINSEAT_P5_SECTION_2.getScore(),FeatureEnumType.TrainSeatP5.getResult(0.06335),0.000001);
//        Assert.assertEquals(FeatureEnumType.TrainSeatP5.TRAINSEAT_P5_SECTION_4.getScore(),FeatureEnumType.TrainSeatP5.getResult(0.17445),0.000001);
//    }
//
//    //    0.404865
////              violate_prob<0.18556908	-40.77655691
////            0.18556908<=violate_prob<0.26205807	-5.943740252
////            0.26205807<=violate_prob<0.38327137	1.015993985
////            0.38327137<=violate_prob<0.46867121	18.39389846
////            0.46867121<=violate_prob<0.55230129	31.99043784
////            0.55230129<=violate_prob<0.64622851	43.67220693
////            0.64622851<=violate_prob	71.10199463
//
//    @Test
//    public void violateProbTest() throws Exception {
//        Assert.assertEquals(FeatureEnumType.ViolateProb.VIOLATE_PROB_SECTION_EMPTY.getScore(),FeatureEnumType.ViolateProb.getResult(-1.0),0.000001);
//        Assert.assertEquals(FeatureEnumType.ViolateProb.VIOLATE_PROB_SECTION_EMPTY.getScore(),FeatureEnumType.ViolateProb.getResult(null),0.000001);
//
//        Assert.assertEquals(FeatureEnumType.ViolateProb.VIOLATE_PROB_SECTION_1.getScore(),FeatureEnumType.ViolateProb.getResult(0.18556907),0.000001);
//        Assert.assertEquals(FeatureEnumType.ViolateProb.VIOLATE_PROB_SECTION_2.getScore(),FeatureEnumType.ViolateProb.getResult(0.18556908),0.000001);
//        Assert.assertEquals(FeatureEnumType.ViolateProb.VIOLATE_PROB_SECTION_7.getScore(),FeatureEnumType.ViolateProb.getResult(0.64622851),0.000001);
//        Assert.assertEquals(FeatureEnumType.ViolateProb.VIOLATE_PROB_SECTION_7.getScore(),FeatureEnumType.ViolateProb.getResult(0.64622852),0.000001);
//    }
//
//    //EMPTY(-1, 0.404865),
////    ZERO(0,   53.49942655),
////    ONE (1,   22.75120195);
//
////    "if  paywayfir_rate < 0.1210
////    and violate_prob >= 0.4783
////    and (land_rate < 1.2325 or land_rate =.)
////    then zuhe18=0;
////else zuhe18=1;"
//
//    @Test
//    public void featureCombination18Test() throws Exception {
//
//        Assert.assertEquals(FeatureEnumType.FeatureCombination18.EMPTY.getScore(),FeatureEnumType.FeatureCombination18.getResult("xx:0.0", -1.0, 0.0),0.000001);
//        Assert.assertEquals(FeatureEnumType.FeatureCombination18.ZERO.getScore(),FeatureEnumType.FeatureCombination18.getResult("xx:0.1209", 0.4783, 1.2324),0.000001);
//
//        Assert.assertEquals(FeatureEnumType.FeatureCombination18.ONE.getScore(),FeatureEnumType.FeatureCombination18.getResult("xx:0.1210", 0.4783, 1.2324),0.000001);
//        Assert.assertEquals(FeatureEnumType.FeatureCombination18.ONE.getScore(),FeatureEnumType.FeatureCombination18.getResult("xx:0.1209", 0.4783, 1.2325),0.000001);
//        Assert.assertEquals(FeatureEnumType.FeatureCombination18.ONE.getScore(),FeatureEnumType.FeatureCombination18.getResult("xx:0.1209", 0.4782, 1.2324),0.000001);
//
//        Assert.assertEquals(FeatureEnumType.FeatureCombination18.ONE.getScore(),FeatureEnumType.FeatureCombination18.getResult("xx:0.1209", null, 1.2324),0.000001);
//        Assert.assertEquals(FeatureEnumType.FeatureCombination18.ONE.getScore(),FeatureEnumType.FeatureCombination18.getResult("xx:0.1209", 0.4783, null),0.000001);
//    }
//
//    //    "if  paywaysec_rate < 0.1390
////    and violate_prob >= 0.4812
////    and planeseat_p3 >= 0.0587
////    then zuhe12=0;
////else zuhe12=1;"
//
//    @Test
//    public void featureCombination12Test() throws Exception {
//
//        Assert.assertEquals(FeatureEnumType.FeatureCombination12.EMPTY.getScore(),FeatureEnumType.FeatureCombination12.getResult("xx:0.0", -1.0, 0.0),0.000001);
//        Assert.assertEquals(FeatureEnumType.FeatureCombination12.ZERO.getScore(),FeatureEnumType.FeatureCombination12.getResult("xx:0.1389", 0.4812, 0.0587),0.000001);
//
//        Assert.assertEquals(FeatureEnumType.FeatureCombination12.ONE.getScore(),FeatureEnumType.FeatureCombination12.getResult("xx:0.1390", 0.4812, 0.0587),0.000001);
//        Assert.assertEquals(FeatureEnumType.FeatureCombination12.ONE.getScore(),FeatureEnumType.FeatureCombination12.getResult("xx:0.1389", 0.4811, 0.0587),0.000001);
//        Assert.assertEquals(FeatureEnumType.FeatureCombination12.ONE.getScore(),FeatureEnumType.FeatureCombination12.getResult("xx:0.1389", 0.4811, 0.0586),0.000001);
//
//        Assert.assertEquals(FeatureEnumType.FeatureCombination12.ONE.getScore(),FeatureEnumType.FeatureCombination12.getResult("xx:0.1389", null, 0.0587),0.000001);
//        Assert.assertEquals(FeatureEnumType.FeatureCombination12.ONE.getScore(),FeatureEnumType.FeatureCombination12.getResult("xx:0.1389", 0.4812, null),0.000001);
//    }
//
//    //    "
////            if paywaythr_rate < 0.1320 and
////    cc_maxamount < 1725.0000
////    then zuhe9=0;
////else zuhe9=1;"
//
//    @Test
//    public void featureCombination9Test() throws Exception {
//
//        Assert.assertEquals(FeatureEnumType.FeatureCombination9.ZERO.getScore(),FeatureEnumType.FeatureCombination9.getResult("xx:0.1319", 1724.0000),0.000001);
//
//        Assert.assertEquals(FeatureEnumType.FeatureCombination9.ONE.getScore(),FeatureEnumType.FeatureCombination9.getResult("xx:0.1320", 1724.0000),0.000001);
//        Assert.assertEquals(FeatureEnumType.FeatureCombination9.ONE.getScore(),FeatureEnumType.FeatureCombination9.getResult("xx:0.1319", 1725.0000),0.000001);
//        Assert.assertEquals(FeatureEnumType.FeatureCombination9.ONE.getScore(),FeatureEnumType.FeatureCombination9.getResult("xx:0.1319", null),0.000001);
//
//        Assert.assertEquals(0.0,FeatureEnumType.FeatureCombination9.getResult(null, 1724.0000),0.000001);
//
//    }
//
//    @Test
//    public void test(){
//        Assert.assertFalse(FeatureEnumType.isNotContainDoubleValue("xx:0.12"));
//        Assert.assertFalse(FeatureEnumType.isNotContainDoubleValue("xx:12"));
//
//        Assert.assertTrue(FeatureEnumType.isNotContainDoubleValue("0.12"));
//        Assert.assertTrue(FeatureEnumType.isNotContainDoubleValue("xx:"));
//        Assert.assertTrue(FeatureEnumType.isNotContainDoubleValue(""));
//    }
//
//
//}
