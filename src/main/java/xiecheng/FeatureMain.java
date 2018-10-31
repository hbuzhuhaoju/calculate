package xiecheng;

import java.math.BigDecimal;

/**
 * Created by zhuhaoju on 2018/3/1.
 * 评分小于368分的客户，通过；>=368分的客户，拒绝。
 */

public class FeatureMain {

//    private static final double CRITICAL_VALUE = 368.0;
//
//    public static void main(String[] args) throws Exception {
//
//        Feature feature = new Feature();
//        feature.setAppLikeMissing(0);
//        feature.setCreditCardMaxAmount(1650.0);
//        feature.setOutPlan(0.216);
//
//        feature.setPayFirst("支付宝:0.3281");
//        feature.setPaySecond("信用卡:0.2813");
//        feature.setPayThrid("储蓄卡:0.1875");
//
//        feature.setPlaneAddrP2(0.0256);
//        feature.setPlaneAddrP3(0.9487);
//
//        feature.setTrainseatP2(0.0625);
//        feature.setTrainseatP4(0.0625);
//        feature.setTrainseatP6(0.5000);
//        feature.setTrainseatP7(0.3750);
//        feature.setTrainseatP3(0.0625);
//        feature.setTrainseatP5(0.1875);
//
//        feature.setViolateProb(0.281649203);
//
//        feature.setLandRate(3.543);
//
//        BigDecimal bigDecimal = BigDecimal.ZERO;
//        bigDecimal = bigDecimal.add(BigDecimal.valueOf(FeatureEnumType.AppLikeMissing.getResult(feature.getAppLikeMissing())))
//                .add(BigDecimal.valueOf(FeatureEnumType.CreditCardMaxAmount.getResult(feature.getCreditCardMaxAmount())))
//                .add(BigDecimal.valueOf(FeatureEnumType.OutPlan.getResult(feature.getOutPlan())))
//                .add(BigDecimal.valueOf(FeatureEnumType.PayFirst.getResult(feature.getPayFirst())))
//                .add(BigDecimal.valueOf(FeatureEnumType.PaySecond.getResult(feature.getPaySecond())))
//                .add(BigDecimal.valueOf(FeatureEnumType.PlaneAddrHighMax.getResult(new Double[]{feature.getPlaneAddrP2(),feature.getPlaneAddrP3()})))
//                .add(BigDecimal.valueOf(FeatureEnumType.TrainHighMax.getResult(new Double[]{feature.getTrainseatP2(),feature.getTrainseatP4(),feature.getTrainseatP6(),feature.getTrainseatP7()})))
//                .add(BigDecimal.valueOf(FeatureEnumType.TrainSeatP3.getResult(feature.getTrainseatP3())))
//                .add(BigDecimal.valueOf(FeatureEnumType.TrainSeatP5.getResult(feature.getTrainseatP5())))
//                .add(BigDecimal.valueOf(FeatureEnumType.ViolateProb.getResult(feature.getViolateProb())))
//                .add(BigDecimal.valueOf(FeatureEnumType.FeatureCombination12.getResult(feature.getPaySecond(),feature.getViolateProb(),feature.getPlaneAddrP3())))
//                .add(BigDecimal.valueOf(FeatureEnumType.FeatureCombination18.getResult(feature.getPayFirst(),feature.getViolateProb(),feature.getLandRate())))
//                .add(BigDecimal.valueOf(FeatureEnumType.FeatureCombination9.getResult(feature.getPayThrid(),feature.getCreditCardMaxAmount())))
//        ;
//        System.out.println(bigDecimal.setScale(0, BigDecimal.ROUND_UP).compareTo(BigDecimal.valueOf(CRITICAL_VALUE)) == -1);
//    }
}
