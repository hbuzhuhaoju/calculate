package ReadDocHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuhaoju on 2018/1/30.
 */
public class TextHandleHelper {

    private static String[] ChineseInterpunction = new String[]{"“", "”", "‘", "’", "。", "，", "；", "：", "？", "！", "……", "—", "～", "（", "）", "《", "》" };
    private static String[] EnglishInterpunction = new String[]{ "\"", "\"", "'", "'", ".", ",", ";", ":", "?", "!", "…", "-", "~", "(", ")", "<", ">" };

    private static String REGEX = "([^-,.?:;'\"!`()a-zA-Z0-9and \\s{1}])";

    private static String SPLIT_REGEX = "[.?!。？！]";
    private static String SPLIT_REGEX_MARKS = "[.?!。？！]{1}\"";

    public static List<Data> handler(List<String> texts){
        List<Data> datas = new ArrayList<>();
        for (String str : texts){
            List<String> list = new ArrayList<>();
            split(str,list);
            for (String s :list){
                datas.add(replaceChnesInterpunction2Data(s));
            }
        }

        return  datas;
    }

    public static List<String> split(String str,List<String> list){
        str = str.replaceAll("“", "\"").replaceAll("”", "\"").trim();
        Pattern pattern = Pattern.compile(SPLIT_REGEX);
        Matcher matcher = pattern.matcher(str);
        int start = str.length();
        if(matcher.find()){
            if(start == matcher.start()){
                list.add(str);
                return list;
            }else {
                start = matcher.start();
            }
        }else{
            list.add(str);
            return list;
        }
       if(str.contains("\"")){
           Pattern patternMark = Pattern.compile(SPLIT_REGEX_MARKS);
           Matcher matcherMark = patternMark.matcher(str);
           if(matcherMark.find(1)){
               start = matcherMark.start();
           }
       }
       list.add(str.substring(0,start+1));
       if(!str.substring(start+1).equals("") ){
           return split(str.substring(start+1),list);
       }else{
           return list;
       }

    }

    public static Data replaceChnesInterpunction2Data(String str){
        String trim = str.trim();
        Pattern pattern =Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(trim);

        Data data = new Data();
        boolean isChange = false;
        List<String> needReplceStrList = new ArrayList<>();
        while (matcher.find()){
            isChange = true;
            needReplceStrList.add(matcher.group());
        }
        for (String s : needReplceStrList){
            trim = trim.replaceAll(s,relpacleStr(s));
        }
        data.setChange(isChange);
        data.setText(trim);
        return  data;
    }

    public static String relpacleStr(String chineseStr){
        List<String> chineseInterpunctions = Arrays.asList(ChineseInterpunction);
        if(chineseInterpunctions.contains(chineseStr)){
            return EnglishInterpunction[chineseInterpunctions.indexOf(chineseStr)];
        }
        return chineseStr;
    }


}
