package StatictisFiledSize;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by zhuhaoju on 2018/2/13.
 */
public class HandleFiledSize {

    private static String[] interpunctions = new String[]{" ","“", "”", "‘", "’", "。", "，", "；", "：", "？", "！", "……", "—", "～", "（", "）", "《", "》","\"", "\"", "'", "'", ".", ",", ";", ":", "?", "!", "…", "-", "~", "(", ")", "<", ">" };

    private static final Integer subIndex = 12;

    public static void main(String[] args) throws Exception {

        String path = HandleFiledSize.class.getResource("").getPath();
        path = path.replaceAll("file:/", "");
        File parentFile = new File(path).getParentFile().getParentFile().getParentFile();

        System.out.println("请输入需要输入Excel名称");
        String excelName = new Scanner(System.in).nextLine();

        System.out.println("请输入需要输入改变列");
        int cellNum = new Scanner(System.in).nextInt()-1;


        handler(parentFile.getPath() + "/filed/", parentFile.getPath() + "/" + excelName + ".xlsx", null, cellNum);
        System.out.println("执行完成，修改的为" + cellName(parentFile.getPath() + "/" + excelName + ".xlsx",cellNum));
    }


    public static void handler (String FileName,String excleFileName,String sheetName,Integer cellIndex)throws Exception{
        File file = new File(FileName);

        Map<String, Integer> stringIntegerMap = cellIndexMap(new File(excleFileName), 0, sheetName);
        Map<String,Long> map = new HashMap<>();
        handlerAllFile(file,map,0L);

        List<RowCellValue> list = new ArrayList<>();
        for (Map.Entry<String,Long> entry:map.entrySet()) {
            DecimalFormat df   = new DecimalFormat("######0.00");
            Integer rowIndexSimple = stringIntegerMap.get(entry.getKey());
            if(rowIndexSimple != null){
                if(entry.getValue()/1024/1024 <1){
                    list.add(new RowCellValue(rowIndexSimple, cellIndex,String.valueOf(df.format(entry.getValue()/1024f)) +"kb"));
                }else{
                    list.add(new RowCellValue(rowIndexSimple, cellIndex,String.valueOf(df.format(entry.getValue()/1024.0f/1024.0f)) +"mb"));
                }
            }
        }

        ExcelUtil.writeExcelCell(new File(excleFileName), 0, sheetName,list);
    }

    public static long handlerAllFile(File file, Map<String,Long> map, Long length){

        if (file.isDirectory()){
            long ChildLengths = 0L;
            File[] files = file.listFiles();
            for (File childFile : files) {
                ChildLengths += handlerAllFile(childFile,map,ChildLengths);
            }
            if(map.get(file.getName()) == null){
                String parentFileName = file.getParentFile().getName();
                map.put(filedName(parentFileName,file.getName()),ChildLengths);
            }

            return ChildLengths;
        }else {
            return file.length();
        }

    }

    public static Map<String,Integer> cellIndexMap(File file, int sheetIndex, String sheetName) throws Exception {
        Map<String,Integer> map = new HashMap<>();
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file)); // 读取的文件
        XSSFSheet sheet = null;
        if(StringUtils.isNotEmpty(sheetName)){
            sheetIndex = workbook.getSheetIndex(sheetName); // sheet表名
        }
        sheet = workbook.getSheetAt(sheetIndex);
        String parentFiledName = null;
        for (int j = 1; j < sheet.getLastRowNum(); j++) {// getLastRowNum
            // 获取最后一行的行标
            XSSFRow row = sheet.getRow(j);
            if (row != null) {
                XSSFCell parentFiledCell = row.getCell(5);
                String newFiledName = null;
                if(parentFiledCell != null && StringUtils.isNotEmpty(newFiledName = parentFiledCell.toString())){
                    parentFiledName = newFiledName;
                }

                XSSFCell childCell = row.getCell(6);
                if(childCell != null){
                    String childFiledName = childCell.toString();
                    Integer integer = map.get(filedName(parentFiledName,childFiledName));
                    if(integer == null){
                        map.put(filedName(parentFiledName,childFiledName),j);
                    }
                }

            }
        }
        return  map;
    }

    public static String cellName(String excleFileName,int cellNum) throws Exception {
        File file = new File(excleFileName);
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file)); // 读取的文件
        XSSFSheet sheet  = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        XSSFCell cell = row.getCell(cellNum);
        if(cell != null){
            return cell.toString();
        }
        return "该列没有列名";
    }

    private static String filedName(String parentFiledName ,String childFiledName){

        childFiledName = childFiledName.toString().replaceAll(" ", "").trim();

        List<String> list = Arrays.asList(interpunctions);

        char[] chars = parentFiledName.toCharArray();
        StringBuffer stb = new StringBuffer();
        for (char filedChar: chars) {
            if(!list.contains(String.valueOf(filedChar))){
                stb.append(filedChar);
            }
        }

        return stb.append("_").append(childFiledName).toString();
    }


}
