package xiecheng;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by zhuhaoju on 2018/2/28.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Feature {

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
