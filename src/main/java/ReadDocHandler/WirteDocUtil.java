package ReadDocHandler;

import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.List;

/**
 * Created by zhuhaoju on 2018/1/30.
 */
public class WirteDocUtil {

    //http://blog.csdn.net/fkbush/article/details/51543242

    public static void writeDoc(String filedPath,List<Data> dataList) {

        OutputStream ostream=null;
        try {
            ostream = new FileOutputStream(filedPath);
            XWPFDocument document = new XWPFDocument();
            //添加一个段落
            int count = 1;
            for (Data data : dataList){
                XWPFParagraph p1 = document.createParagraph();
                p1.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun r1=p1.createRun();//p1.createRun()将一个新运行追加到这一段
                r1.setText(count + "." +data.getText());
                r1.setFontFamily("times new Roman");//---设置ASCII(0 - 127)字体样式
                r1.setFontSize(15);//---字体大小
                if(data.isChange()){
                    r1.setColor("33CC00");
                }
                count ++;
            }
//                                    //setAlignment()指定应适用于此段落中的文本的段落对齐方式。CENTER LEFT...
//                                  //p1.setAlignment(ParagraphAlignment.LEFT);
//                                    //p1.setBorderBetween(Borders.APPLES);
//                                    //p1.setBorderBottom(Borders.APPLES);
//                                    //p1.setBorderLeft(Borders.APPLES);指定应显示在左边页面指定段周围的边界。
//                                    //p1.setBorderRight(Borders.ARCHED_SCALLOPS);指定应显示在右侧的页面指定段周围的边界。
//                                    //p1.setBorderTop(Borders.ARCHED_SCALLOPS);指定应显示上方一组有相同的一组段边界设置的段落的边界。这几个是对段落之间的格式的统一，相当于格式刷
//                                   //p1.setFirstLineIndent(99);//---正文宽度会稍微变窄
//                                   //p1.setFontAlignment(1);//---段落的对齐方式 1左 2中 3右 4往上 左 不可写0和负数
//                                    //p1.setIndentationFirstLine(400);//---首行缩进,指定额外的缩进，应适用于父段的第一行。
//                                  //p1.setIndentationHanging(400);//---首行前进,指定的缩进量，应通过第一行回到开始的文本流的方向上移动缩进从父段的第一行中删除。
//                                   //p1.setIndentationLeft(400);//---整段缩进（右移）指定应为从左到右段，该段的内容的左边的缘和这一段文字左边的距和右边文本边距和左段权中的那段文本的右边缘之间的缩进,如果省略此属性，则应假定其值为零。
//                                   //p1.setIndentationRight(400);//---指定应放置这一段，该段的内容从左到右段的右边缘的正确文本边距和右边文本边距和左段权中的那段文本的右边缘之间的缩进,如果省略此属性，则应假定其值为零。
//                                    //p1.setIndentFromLeft(400);//---整段右移
//                                    //p1.setIndentFromRight(400);
//                                   //p1.setNumID(BigInteger.TEN);
//                                    //p1.setPageBreak(true);//--指定当渲染此分页视图中的文档，这一段的内容都呈现在文档中的新页的开始。
//                                    //p1.setSpacingAfter(6);//--指定应添加在文档中绝对单位这一段的最后一行之后的间距。
//                                    //p1.setSpacingAfterLines(6);//--指定应添加在此线单位在文档中的段落的最后一行之后的间距。
//                                  //p1.setSpacingBefore(6);//--指定应添加上面这一段文档中绝对单位中的第一行的间距。
//                                     //p1.setSpacingBeforeLines(6);//--指定应添加在此线单位在文档中的段落的第一行之前的间距。
//                                     //p1.setSpacingLineRule(LineSpacingRule.AT_LEAST);//--指定行之间的间距如何计算存储在行属性中。
//                                    //p1.setStyle("");//--此方法提供了样式的段落，这非常有用.
//                                    //p1.setVerticalAlignment(TextAlignment.CENTER);//---指定的文本的垂直对齐方式将应用于此段落中的文本
//                                    //p1.setWordWrapped(true);//--此元素指定是否消费者应中断超过一行的文本范围，通过打破这个词 （打破人物等级） 的两行或通过移动到下一行 （在词汇层面上打破） 这个词的拉丁文字。
//            XWPFRun r1=p1.createRun();//p1.createRun()将一个新运行追加到这一段
//                                     //setText(String value)或
//                                     //setText(String value,int pos)
//                                     //value - the literal text which shall be displayed in the document
//                                    //pos - - position in the text array (NB: 0 based)
//            r1.setText(data);
            //r1.setTextPosition(20);//这个相当于设置行间距的，具体这个20是怎么算的，不清楚,此元素指定文本应为此运行在关系到周围非定位文本的默认基线升降的量。不是真正意义上的行间距
            //---This element specifies the amount by which text shall be ★raised or lowered★ for this run in relation to the default baseline of the surrounding non-positioned text.
            //r1.setStrike(true);//---设置删除线的,坑人!!!
            //r1.setStrikeThrough(true);---也是设置删除线，可能有细微的区别吧
            //r1.setEmbossed(true);---变的有重影（变黑了一点）
            //r1.setDoubleStrikethrough(true);---设置双删除线
            //r1.setColor("33CC00");//---设置字体颜色 ★
            //r1.setFontFamily("fantasy");
//            r1.setFontFamily("times new Roman");//---设置ASCII(0 - 127)字体样式
//                                     r1.setBold(jiacu);//---"加黑加粗"
//            r1.setFontSize(15);//---字体大小
            //r1.setImprinted(true);//感觉与setEmbossed(true)类似，有重影
            //r1.setItalic(true);//---文本会有倾斜，是一种字体？
            //r1.setShadow(true);//---文本会变粗有重影，与前面两个有重影效果的方法感觉没什么区别
            //r1.setSmallCaps(true);//---改变了  英文字母  的格式
            //r1.setSubscript(VerticalAlign.BASELINE);//---valign垂直对齐的
            //r1.setUnderline(UnderlinePatterns.DASH);//--填underline type设置下划线
            //document.createTable(2, 2);//--创建一个制定行列的表
            //document.enforceReadonlyProtection();//--强制执行制度保护


            /**
             * r1.setDocumentbackground(doc, "FDE9D9");//设置页面背景色
             r1.testSetUnderLineStyle(doc);//设置下划线样式以及突出显示文本
             r1.addNewPage(doc, BreakType.PAGE);
             r1.testSetShdStyle(doc);//设置文字底纹
             */
            document.write(ostream);
            System.out.println("创建word成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(ostream!=null){
                try {
                    ostream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
