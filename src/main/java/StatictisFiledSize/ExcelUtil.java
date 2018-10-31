package StatictisFiledSize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by zhuhaoju on 2018/2/13.
 */
public class ExcelUtil{


    public enum ExcelTypeEnum{

        EXCEL_2003("xls",new Excel2003Util()),
        EXCEL_2007("xlsx",new Excel2007Util());

        String name;
        ParentExcelUtil handlerUtil;

        ExcelTypeEnum(String name, ParentExcelUtil handlerUtil) {
            this.name = name;
            this.handlerUtil = handlerUtil;
        }

        public String getName() {
            return name;
        }

        public ParentExcelUtil getHandlerUtil() {
            return handlerUtil;
        }
    }

    public static void writeExcelCell(File file, int sheetIndex, String sheetName, List<RowCellValue> rowCellValues) throws Exception {
        partFile(file).getHandlerUtil().insertExcelCell(file,sheetIndex,sheetName,rowCellValues);
    }

    public static List<String[]> readExcelCell(File file, int sheetIndex, String sheetName) throws Exception {
        return null;
    }



    public static ExcelTypeEnum partFile(File file){
        String fileName = file.getName();
        int iIndex = fileName.lastIndexOf(".");
        String ext = (iIndex < 0) ? "" : fileName.substring(iIndex + 1).toLowerCase();
        if (!"xls,xlsx".contains(ext) || "".contains(ext)) {
            throw  new RuntimeException("文件类型不是EXCEL！");
        }
        for (ExcelTypeEnum typeEnum: ExcelTypeEnum.values()) {
            String name = typeEnum.getName();
            if (ext.equals(name)){
                return typeEnum;
            }
        }
        return null;
    }


}
