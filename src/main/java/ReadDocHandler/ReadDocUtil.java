package ReadDocHandler;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuhaoju on 2018/1/30.
 */
public class ReadDocUtil {

    public static List<String> readDoc(String filePath){
        if(filePath.endsWith("doc")){
            return readWord2003(filePath);
        }else if(filePath.endsWith("docx")){
            return readWord2007(filePath);
        }else {
            throw new RuntimeException("非word文件" + filePath);
        }
    }

    private static List<String> readWord2007(String filePath) {
        try {
            OPCPackage oPCPackage = POIXMLDocument.openPackage(filePath);
            XWPFDocument xwpf = new XWPFDocument(oPCPackage);
            List<XWPFParagraph> paragraphs = xwpf.getParagraphs();

            List<String> paragraphList = new ArrayList<>();
            paragraphs.stream().forEach(xwpfParagraph->paragraphList.add(xwpfParagraph.getText()));

            return paragraphList;
        } catch (Exception e) {
            throw new RuntimeException("解析文件异常" + filePath);
        }
    }

    private static List<String> readWord2003(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            WordExtractor wordExtractor = new WordExtractor(fis);
            String[] paragraphTexts = wordExtractor.getParagraphText();
            List<String> paragraphList = new ArrayList<>();
            for (String paragraphText:paragraphTexts) {
                paragraphList.add(paragraphText);
            }
            return paragraphList;
        }catch (Exception e) {
            throw new RuntimeException("解析文件异常" + filePath);
        }
    }


}
