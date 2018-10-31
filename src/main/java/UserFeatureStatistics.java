import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by zhuhaoju on 2018/3/1.
 */
public class UserFeatureStatistics {

    private static final String REGEX = ".*:\\d+\\.{0,1}\\d*$";

    private static final double CRITICAL_VALUE = 368.0;

    private static final double DEFAULT_VALUE = 0.0;

    public static void main(String[] args) throws Exception {

        Feature feature = new Feature();
        feature.setAppLikeMissing(0);
        feature.setCreditCardMaxAmount(1650.0);
        feature.setOutPlan(0.216);

        feature.setPayFirst("支付宝:0.3281");
        feature.setPaySecond("信用卡:0.2813");
        feature.setPayThrid("储蓄卡:0.1875");

        feature.setPlaneAddrP2(0.0256);
        feature.setPlaneAddrP3(0.9487);

        feature.setTrainseatP2(0.0625);
        feature.setTrainseatP4(0.0625);
        feature.setTrainseatP6(0.5000);
        feature.setTrainseatP7(0.3750);
        feature.setTrainseatP3(0.0625);
        feature.setTrainseatP5(0.1875);

        feature.setViolateProb(0.281649203);

        feature.setLandRate(3.543);

        BigDecimal bigDecimal = BigDecimal.ZERO;
        bigDecimal = bigDecimal.add(BigDecimal.valueOf(AppLikeMissing.getResult(feature.getAppLikeMissing())))
                .add(BigDecimal.valueOf(CreditCardMaxAmount.getResult(feature.getCreditCardMaxAmount())))
                .add(BigDecimal.valueOf(OutPlan.getResult(feature.getOutPlan())))
                .add(BigDecimal.valueOf(PayFirst.getResult(feature.getPayFirst())))
                .add(BigDecimal.valueOf(PaySecond.getResult(feature.getPaySecond())))
                .add(BigDecimal.valueOf(PlaneAddrHighMax.getResult(new Double[]{feature.getPlaneAddrP2(),feature.getPlaneAddrP3()})))
                .add(BigDecimal.valueOf(TrainHighMax.getResult(new Double[]{feature.getTrainseatP2(),feature.getTrainseatP4(),feature.getTrainseatP6(),feature.getTrainseatP7()})))
                .add(BigDecimal.valueOf(TrainSeatP3.getResult(feature.getTrainseatP3())))
                .add(BigDecimal.valueOf(TrainSeatP5.getResult(feature.getTrainseatP5())))
                .add(BigDecimal.valueOf(ViolateProb.getResult(feature.getViolateProb())))
                .add(BigDecimal.valueOf(FeatureCombination12.getResult(feature.getPaySecond(),feature.getViolateProb(),feature.getPlaneAddrP3())))
                .add(BigDecimal.valueOf(FeatureCombination18.getResult(feature.getPayFirst(),feature.getViolateProb(),feature.getLandRate())))
                .add(BigDecimal.valueOf(FeatureCombination9.getResult(feature.getPayThrid(),feature.getCreditCardMaxAmount())))
        ;
        System.out.println(bigDecimal.setScale(0, BigDecimal.ROUND_UP).compareTo(BigDecimal.valueOf(CRITICAL_VALUE)) == -1);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Feature {

        /**
         * app粘度缺失标识
         */
        public Integer appLikeMissing;

        /**
         * 信用卡最大消费
         */
        public double creditCardMaxAmount;

        /**
         * 出行规划完备度
         */
        public double outPlan;

        /**
         * 支付方式：第一、第二、第三
         */
        public String payFirst;

        public String paySecond;

        public String payThrid;

        /**
         * 机票地域倾向[中型机场:大型机场 =p2 : p3]
         */
        public  Double planeAddrP2;

        public  Double planeAddrP3;


        /**
         *  火车票席位倾向[硬座:p1、一等座:p2、二等座:p3、商务:p4、硬卧:p5、软卧:p6、高软:p7]
         */
        public Double trainseatP3;

        public Double trainseatP2;

        public Double trainseatP4;

        public Double trainseatP6;

        public Double trainseatP7;


        public Double trainseatP5;

        /**
         * 违约概率
         */
        public Double violateProb;

        /**
         *登录频率
         */
        public Double  landRate;

    }



    /**
     * app粘度缺失标识
     *
     * app_like_missing=0	15.47176998
     * app_like_missing=1	27.71460613
     */

    @AllArgsConstructor
    @NoArgsConstructor
    public enum  AppLikeMissing{
        IS_MISS(0,15.47176998),
        IS_NOT_MISS(1,27.71460613);

        @Getter
        private int value;

        @Getter
        private double score;

        public static double getResult(Integer value)  {
            if(value == null){
                return IS_MISS.getScore();
            }
            return getEqualValue(AppLikeMissing.class,value);
        }
    }


    /*
     * 信用卡最大消费金额
     *
     * cc_maxamount<25	        12.74475431
     * 25<=cc_maxamount<1725	22.71531911
     * 1725<=cc_maxamount<4775	35.33297735
     * 4775<=cc_maxamount<10675	41.7032792
     * 10675<=cc_maxamount	    59.80035726
     */
    @AllArgsConstructor
    @NoArgsConstructor
    public enum  CreditCardMaxAmount{

        MAX_AMOUNT_SECTION_1( -1,  25,      12.74475431),
        MAX_AMOUNT_SECTION_2( 25,  1725,    22.71531911),
        MAX_AMOUNT_SECTION_3(1725, 4775,    35.33297735),
        MAX_AMOUNT_SECTION_4(4775, 10675,   41.7032792),
        MAX_AMOUNT_SECTION_5(10675, -1,     59.80035726);

        @Getter
        private double start;

        @Getter
        private double end;

        @Getter
        private double score;

        public static double getResult(Double value) throws Exception {
            return getBetweenValue(CreditCardMaxAmount.class,value);
        }
    }

    /*
     * 出行规划完备度
     *
     * 将-1填为均值             0.184011
     * outplan<0.059	        41.01935436
     * 0.059<=outplan<0.096	    33.5089669
     * 0.096<=outplan<0.1265	29.3794848
     * 0.1265<=outplan<0.1705	22.83004142
     * 0.1705<=outplan<0.2355	18.19595908
     * 0.2355<=outplan<0.3355	11.04986534
     * 0.3355<=outplan	        -0.036948879
     */


    @AllArgsConstructor
    @NoArgsConstructor
    public enum  OutPlan{

        OUT_PLAN_SECTION_EMPTY( -1,  -1,      0.184011),
        OUT_PLAN_SECTION_1( -1,     0.059,    41.01935436),
        OUT_PLAN_SECTION_2( 0.059,  0.096,    33.5089669),
        OUT_PLAN_SECTION_3(0.096,   0.1265,   29.3794848),
        OUT_PLAN_SECTION_4(0.1265,  0.1705,   22.83004142),
        OUT_PLAN_SECTION_5(0.1705,  0.2355,   18.19595908),
        OUT_PLAN_SECTION_6(0.2355,  0.3355,   11.04986534),
        OUT_PLAN_SECTION_7(0.3355,  -1,       -0.036948879);

        @Getter
        private double start;

        @Getter
        private double end;

        @Getter
        private double score;

        public static double getResult(Double value) {
            if(value == null || value.equals(-1.0)){
                return OUT_PLAN_SECTION_EMPTY.getScore();
            }
            return getBetweenValue(OutPlan.class,value);
        }
    }

    /*
     * 第一支付方式
     *
     * paywayfir in ("钱包","储蓄卡","网银","外币兑换微信支付")	                        55.1035064
     * paywayfir in ("ApplePay","百度钱包","支付宝","微信支付","QQ钱包","银联手机支付")	24.88035364
     * paywayfir in ("担保","信用支付","信用卡","票券")	                                1.715149677
     */

    @AllArgsConstructor
    @NoArgsConstructor
    public enum  PayFirst{

        PAY_METHOD_1( true, Arrays.asList(new String[]{"钱包","储蓄卡","网银","外币兑换微信支付"}),                          55.1035064),
        PAY_METHOD_2( true, Arrays.asList(new String[]{"ApplePay","百度钱包","支付宝","微信支付","QQ钱包","银联手机支付"}),24.88035364),
        PAY_METHOD_3( true, Arrays.asList(new String[]{"担保","信用支付","信用卡","票券"}),                                   1.715149677);

        @Getter
        private boolean isContain;

        @Getter
        private List<String> payMethods;

        @Getter
        private double score;

        public static double getResult(String value) {
            if(StringUtils.isNotEmpty(value) && value.contains(":")){
                return getContainValue(PayFirst.class,value.split(":")[0]);
            }
            return DEFAULT_VALUE;
        }
    }

    /*
     * 第二支付方式
     *
     * paywaysec not in ("ApplePay","百度钱包","支付宝","微信支付","QQ钱包","银联手机支付","担保","信用支付","信用卡","票券","外币兑换微信支付") 	52.21612716
     * paywaysec in ("ApplePay","百度钱包","支付宝","微信支付","QQ钱包","银联手机支付")	                                                            26.26328892
     * paywaysec in ("担保","信用支付","信用卡","票券")                                                                                            	5.420831074
     */

    @AllArgsConstructor
    @NoArgsConstructor
    public enum  PaySecond{

        PAY_METHOD_1( false, Arrays.asList(new String[]{"ApplePay","百度钱包","支付宝","微信支付","QQ钱包","银联手机支付","担保","信用支付","信用卡","票券","外币兑换微信支付"}),52.21612716),
        PAY_METHOD_2( true, Arrays.asList(new String[]{"ApplePay","百度钱包","支付宝","微信支付","QQ钱包","银联手机支付"}),                                                          26.26328892),
        PAY_METHOD_3( true, Arrays.asList(new String[]{"担保","信用支付","信用卡","票券"}),                                                                                             5.420831074);

        @Getter
        private boolean isContain;

        @Getter
        private List<String> payMethods;

        @Getter
        private double score;

        public static double getResult(String value) {
            if(StringUtils.isNotEmpty(value) && value.contains(":")){
                return getContainValue(PaySecond.class,value.split(":")[0]);
            }
            return DEFAULT_VALUE;
        }
    }

    /*
     * 第三种支付方式
     *
     * paywaythr in ("钱包","储蓄卡","网银")	                                                                                        56.556844
     * paywaythr in ("ApplePay","百度钱包","支付宝","微信支付","QQ钱包","银联手机支付")	                                                25.46049826
     * paywaythr not in ("外币兑换微信支付","钱包","储蓄卡","网银","ApplePay","百度钱包","支付宝","微信支付","QQ钱包","银联手机支付")	5.435113406
     */

    @AllArgsConstructor
    @NoArgsConstructor
    public enum  PayThird{

        PAY_METHOD_1( true, Arrays.asList(new String[]{"钱包","储蓄卡","网银"}),                                                                                            56.556844),
        PAY_METHOD_2( true, Arrays.asList(new String[]{"ApplePay","百度钱包","支付宝","微信支付","QQ钱包","银联手机支付"}),                                              25.46049826),
        PAY_METHOD_3( false, Arrays.asList(new String[]{"外币兑换微信支付","钱包","储蓄卡","网银","ApplePay","百度钱包","支付宝","微信支付","QQ钱包","银联手机支付"}),5.435113406);

        @Getter
        private boolean isContain;

        @Getter
        private List<String> payMethods;

        @Getter
        private double score;

        public static double getResult(String value){
            if(StringUtils.isNotEmpty(value) && value.contains(":")){
                return getContainValue(PayThird.class,value.split(":")[0]);
            }
            return DEFAULT_VALUE;
        }
    }


    /*
     * 飞机机场高级别中的最高概率
     *
     * max(planeaddr_p2,planeaddr_p3)=空	20.84603151
     * max(planeaddr_p2,planeaddr_p3)<0.6905	19.11573457
     * 0.6905<=max(planeaddr_p2,planeaddr_p3)<0.83975	20.37055526
     * 0.83975<=max(planeaddr_p2,planeaddr_p3)<0.9298	25.44039442
     * 0.9298<=max(planeaddr_p2,planeaddr_p3)<0.9631	36.2263656
     * 0.9631<=max(planeaddr_p2,planeaddr_p3)	44.12571877
     */


    @AllArgsConstructor
    @NoArgsConstructor
    public enum  PlaneAddrHighMax{

        PLANE_ADDR_HIGH_SECTION_EMPTY( -1,      -1,      20.84603151),
        PLANE_ADDR_HIGH_SECTION_1(     -1,      0.6905,   19.11573457),
        PLANE_ADDR_HIGH_SECTION_2(     0.6905,  0.83975,  20.37055526),
        PLANE_ADDR_HIGH_SECTION_3(     0.83975, 0.9298,   25.44039442),
        PLANE_ADDR_HIGH_SECTION_4(     0.9298,  0.9631,   36.2263656),
        PLANE_ADDR_HIGH_SECTION_5(     0.9631,  -1,       41.7032792);

        @Getter
        private double start;

        @Getter
        private double end;

        @Getter
        private double score;

        public static double getResult(Double[] values){
            List<Double> list = Arrays.asList(values);
            list = list.stream().filter((Double value) -> {return value != null;}).collect(Collectors.toList());
            if(list.isEmpty()){
                return PLANE_ADDR_HIGH_SECTION_EMPTY.getScore();
            }
            return getBetweenValue(PlaneAddrHighMax.class,list.stream().max(Double::compareTo).get());
        }
    }

    /*
     * 火车座席高级别中的最高概率
     * max(trainseat_p2,trainseat_p4,trainseat_p6,trainseat_p7)=空	            26.91912261
     * max(trainseat_p2,trainseat_p4,trainseat_p6,trainseat_p7)<0.4035	        33.34558252
     * 0.4035<=max(trainseat_p2,trainseat_p4,trainseat_p6,trainseat_p7)<0.6021	25.21973133
     * 0.6021<=max(trainseat_p2,trainseat_p4,trainseat_p6,trainseat_p7)<0.7633	22.94892735
     * 0.7633<=max(trainseat_p2,trainseat_p4,trainseat_p6,trainseat_p7)	        17.61004818
     */

    @AllArgsConstructor
    @NoArgsConstructor
    public enum  TrainHighMax{

        TRAIN_HIGH_SECTION_EMPTY( -1,      -1,       26.91912261),
        TRAIN_HIGH_SECTION_1(     -1,      0.4035,   33.34558252),
        TRAIN_HIGH_SECTION_2(     0.4035,  0.6021,   25.21973133),
        TRAIN_HIGH_SECTION_3(     0.6021,  0.7633,   22.94892735),
        TRAIN_HIGH_SECTION_4(     0.7633,  -1,       17.61004818);

        @Getter
        private double start;

        @Getter
        private double end;

        @Getter
        private double score;

        public static double getResult(Double[] values){
            List<Double> list = Arrays.asList(values);
            list = list.stream().filter((Double value) -> {return value != null;}).collect(Collectors.toList());
            if(list.isEmpty()){
                return TRAIN_HIGH_SECTION_EMPTY.getScore();
            }
            return getBetweenValue(TrainHighMax.class,list.stream().max(Double::compareTo).get());
        }
    }

    /*
     * 火车票二等座概率
     *
     * trainseat_p3=空	             24.57906916
     * trainseat_p3<0.0686	        40.73823816
     * 0.0686<=trainseat_p3<0.1165	31.05225096
     * 0.1165<=trainseat_p3<0.1835	21.12393243
     * 0.1835<=trainseat_p3<0.32375	24.38529638
     * 0.32375<=trainseat_p3	    20.11955877
     */

    @AllArgsConstructor
    @NoArgsConstructor
    public enum  TrainSeatP3{

        TRAINSEAT_P3_SECTION_EMPTY( -1,      -1,       24.57906916),
        TRAINSEAT_P3_SECTION_1(     -1,      0.0686,   40.73823816),
        TRAINSEAT_P3_SECTION_2(     0.0686,  0.1165,   31.05225096),
        TRAINSEAT_P3_SECTION_3(     0.1165,  0.1835,   21.12393243),
        TRAINSEAT_P3_SECTION_4(     0.1835,  0.32375,  24.38529638),
        TRAINSEAT_P3_SECTION_5(     0.32375,  -1,      20.11955877);

        @Getter
        private double start;

        @Getter
        private double end;

        @Getter
        private double score;

        public static double getResult(Double value){
            if(value == null){
                return TRAINSEAT_P3_SECTION_EMPTY.getScore();
            }
            return getBetweenValue(TrainSeatP3.class,value);
        }
    }

    /**
     * 火车票硬座概率
     * trainseat_p5=空	            26.72197477
     * trainseat_p5<0.05335	        14.81205684
     * 0.05335<=trainseat_p5<0.1188	21.99801356
     * 0.1188<=trainseat_p5<0.17445	29.96551287
     * 0.17445<=trainseat_p5	    30.53526246
     */

    @AllArgsConstructor
    @NoArgsConstructor
    public enum  TrainSeatP5{

        TRAINSEAT_P5_SECTION_EMPTY( -1,      -1,       26.72197477),
        TRAINSEAT_P5_SECTION_1(     -1,      0.05335,   14.81205684),
        TRAINSEAT_P5_SECTION_2(     0.05335,  0.1188,   21.99801356),
        TRAINSEAT_P5_SECTION_3(     0.1188,  0.17445,   29.96551287),
        TRAINSEAT_P5_SECTION_4(     0.17445,  -1,       30.53526246);

        @Getter
        private double start;

        @Getter
        private double end;

        @Getter
        private double score;

        public static double getResult(Double value) {
            if(value == null){
                return TRAINSEAT_P5_SECTION_EMPTY.getScore();
            }
            return getBetweenValue(TrainSeatP5.class,value);
        }
    }



    /**
     * 违约概率
     *
     * 将-1填为均值                          0.404865
     * violate_prob<0.18556908	            -40.77655691
     * 0.18556908<=violate_prob<0.26205807	-5.943740252
     * 0.26205807<=violate_prob<0.38327137	1.015993985
     * 0.38327137<=violate_prob<0.46867121	18.39389846
     * 0.46867121<=violate_prob<0.55230129	31.99043784
     * 0.55230129<=violate_prob<0.64622851	43.67220693
     * 0.64622851<=violate_prob	            71.10199463
     */

    @AllArgsConstructor
    @NoArgsConstructor
    public enum  ViolateProb{

        VIOLATE_PROB_SECTION_EMPTY( -1,          -1,           0.404865),
        VIOLATE_PROB_SECTION_1(     -1,          0.18556908,   -40.77655691),
        VIOLATE_PROB_SECTION_2(     0.18556908,  0.26205807,   -5.943740252),
        VIOLATE_PROB_SECTION_3(     0.26205807,  0.38327137,   1.015993985),
        VIOLATE_PROB_SECTION_4(     0.38327137,  0.46867121,   18.39389846),
        VIOLATE_PROB_SECTION_5(     0.46867121,  0.55230129,   31.99043784),
        VIOLATE_PROB_SECTION_6(     0.55230129,  0.64622851,   43.67220693),
        VIOLATE_PROB_SECTION_7(     0.64622851,  -1,           71.10199463);

        @Getter
        private double start;

        @Getter
        private double end;

        @Getter
        private double score;

        public static double getResult(Double value) {
            if(value == null || value.equals(-1.0)){
                return VIOLATE_PROB_SECTION_EMPTY.getScore();
            }
            return getBetweenValue(ViolateProb.class,value);
        }
    }

    /**
     * 火车票二等座、第二支付方式、违约组合概率
     *
     * violate_prob:将-1填为均值                             0.404865
     * paywaysec_rate非空 and paywaysec_rate < 0.1390
     * and violate_prob >= 0.4812
     * and  planeseat_p3非空  and planeseat_p3 >= 0.0587	48.5331634
     * else	                                                23.99542493
     */

    @AllArgsConstructor
    @NoArgsConstructor
    public enum  FeatureCombination12{

        EMPTY(-1, 0.404865),
        ZERO(0,   48.5331634042),
        ONE (1,   23.9954249308);

        @Getter
        private int value;

        @Getter
        private double score;

        public static double getResult(String paywaysec,Double violateProb,Double planeAddrP3) {
            if(violateProb != null && violateProb.equals(-1.0)){
                return EMPTY.getScore();
            }else if(isNotContainDoubleValue(paywaysec)){
                return DEFAULT_VALUE;
            }else{
                Integer value = 1;
                Double paywaySecRate = Double.valueOf(paywaysec.split(":")[1]);
                if(paywaySecRate.compareTo(Double.valueOf(0.1390)) == -1
                        && violateProb != null && violateProb.compareTo(Double.valueOf(0.4812)) != -1
                        && planeAddrP3 != null && planeAddrP3.compareTo(Double.valueOf(0.0587)) != -1){
                    value = 0;
                }
                return getEqualValue(FeatureCombination12.class,value);
            }
        }
    }

    /**
     * 第一支付方式、登录账号频率、违约概率
     * violate_prob:将-1填为均值                        0.404865
     * paywayfir_rate非空  and paywayfir_rate < 0.1210
     * and violate_prob >= 0.4783
     * and (land_rate < 1.2325 or land_rate =-1)	    53.49942655
     * else	                                            22.75120195
     */

    @AllArgsConstructor
    @NoArgsConstructor
    public enum  FeatureCombination18{

        EMPTY(-1, 0.404865),
        ZERO(0,   53.49942655),
        ONE (1,   22.75120195);

        @Getter
        private int value;

        @Getter
        private double score;

        public static double getResult(String paywayFir,Double violateProb,Double landRate) {
            if(violateProb != null && violateProb.equals(-1.0)){
                return EMPTY.getScore();
            }else if(isNotContainDoubleValue(paywayFir)){
                return DEFAULT_VALUE;
            }else{
                Integer value = 1;
                Double paywayFirRate = Double.valueOf(paywayFir.split(":")[1]);
                if(paywayFirRate.compareTo(Double.valueOf(0.1210)) == -1
                        && violateProb != null && violateProb.compareTo(Double.valueOf(0.4783)) != -1
                        && landRate != null && (landRate.compareTo(Double.valueOf(1.2325)) == -1 || landRate.equals(-1.0) )){
                    value = 0;
                }
                return getEqualValue(FeatureCombination18.class,value);
            }
        }
    }

    /**
     * 第三支付方式、信用卡最大消费组合概率
     *  paywaythr_rate非空 and paywaythr_rate < 0.1320 and  cc_maxamount非空
     *  cc_maxamount < 1725"	                                                48.2857382
     *  else	                                                                20.9994247
     */



    @AllArgsConstructor
    @NoArgsConstructor
    public enum  FeatureCombination9{

        ZERO(0,   48.2857382),
        ONE (1,   20.99942478);

        @Getter
        private int value;

        @Getter
        private double score;

        public static double getResult(String paywayThir,Double creditCardMaxAmount){
            Integer value = 1;
            if(isNotContainDoubleValue(paywayThir)){
                return DEFAULT_VALUE;
            }else {
                Double paywayThirRate = Double.valueOf(paywayThir.split(":")[1]);
                if(paywayThirRate.compareTo(Double.valueOf(0.1320)) == -1
                        && creditCardMaxAmount != null && creditCardMaxAmount.compareTo(Double.valueOf(1725.0)) == -1){
                    value = 0;
                }
                return getEqualValue(FeatureCombination9.class,value);
            }
        }
    }



    private static double getContainValue(Class enumClass, String value){
        try{
            Object[] enums = enumClass.getEnumConstants();
            Method isContain = enumClass.getMethod("isContain");
            Method getPayMethods = enumClass.getMethod("getPayMethods");
            Object currObject = null;

            for (Object enumType: enums) {
                boolean isFlag = (boolean) isContain.invoke(enumType);
                List<String> payMethods = (List<String>) getPayMethods.invoke(enumType);
                if(isFlag ? payMethods.contains(value) : !payMethods.contains(value) ){
                    currObject = enumType;
                    break;
                }
            }
            Method getScore = enumClass.getMethod("getScore");
            return  (Double) getScore.invoke(currObject);
        }catch (Exception e){
            return  DEFAULT_VALUE;
        }
    }


    private static double getBetweenValue(Class enumClass, Double value){
        try{
            Object[] enums = enumClass.getEnumConstants();
            Method getStart = enumClass.getMethod("getStart");
            Method getEnd = enumClass.getMethod("getEnd");
            Object currObject = null;

            for (Object enumType: enums) {
                double start = (Double) getStart.invoke(enumType);
                double end =   (Double) getEnd.invoke(enumType);

                if(start == -1 && value.compareTo(end) == -1){
                    currObject = enumType;
                    break;
                }else if(end == -1 && start != -1 && value.compareTo(start) != -1){
                    currObject = enumType;
                    break;
                }else if(value.compareTo(start) != -1 && value.compareTo(end) == -1){
                    currObject = enumType;
                    break;
                }
            }
            Method getScore = enumClass.getMethod("getScore");
            return  (Double) getScore.invoke(currObject);
        }catch (Exception e){
            return DEFAULT_VALUE;
        }
    }

    private static double getEqualValue(Class enumClass, Integer appLikeMissingValue)  {
        try{
            Object[] enums = enumClass.getEnumConstants();
            Method getValue = enumClass.getMethod("getValue");
            Object currObject = null;

            for (Object enumType: enums) {
                Integer value = (Integer) getValue.invoke(enumType);
                if(value.equals(appLikeMissingValue)){
                    currObject = enumType;
                    break;
                }
            }
            Method getScore = enumClass.getMethod("getScore");
            return  (Double) getScore.invoke(currObject);
        }catch (Exception e){
            return DEFAULT_VALUE;
        }
    }

    public static boolean isNotContainDoubleValue(String value){
        if(StringUtils.isEmpty(value)){
            return true;
        }else{
            return !Pattern.matches(REGEX,value);
        }
    }

}
