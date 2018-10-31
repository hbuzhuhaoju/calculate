package StatictisFiledSize;

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
public class Excel2007Util extends ParentExcelUtil {


    @Override
    public void insertExcelCell(File file, int sheetIndex, String sheetName, List<RowCellValue> rowCellValues) throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file)); // 读取的文件
        XSSFSheet sheet = null;
        if(StringUtils.isNotEmpty(sheetName)){
            sheetIndex = workbook.getSheetIndex(sheetName); // sheet表名
        }
        sheet = workbook.getSheetAt(sheetIndex);


        for (RowCellValue rowCellValue: rowCellValues) {
            XSSFRow row = sheet.getRow(rowCellValue.getRowIndex()); // 获取指定的行对象，无数据则为空，需要创建
            if (row == null) {
                row = sheet.createRow(rowCellValue.getRowIndex()); // 该行无数据，创建行对象
            }
            Cell cell = row.createCell(rowCellValue.getCellIndex()); // 创建指定单元格对象。如本身有数据会替换掉
            cell.setCellValue(rowCellValue.getValue());
        }

        FileOutputStream fo = new FileOutputStream(file); // 输出到文件
        workbook.write(fo);
    }

    @Override
    public List<List<String>> readExcelCell(File file, int sheetIndex, String sheetName) {
        return null;
    }
}
