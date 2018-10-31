package StatictisFiledSize;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by zhuhaoju on 2018/2/13.
 */
public class Excel2003Util extends ParentExcelUtil {


    @Override
    public void insertExcelCell(File file, int sheetIndex, String sheetName, List<RowCellValue> rowCellValues) throws Exception {

        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file)); // 读取的文件
        HSSFSheet sheet = null;
        if(StringUtils.isNotEmpty(sheetName)){
            sheetIndex = workbook.getSheetIndex(sheetName); // sheet表名
        }
        sheet = workbook.getSheetAt(sheetIndex);


        for (RowCellValue rowCellValue: rowCellValues) {
            HSSFRow row = sheet.getRow(rowCellValue.getRowIndex()); // 获取指定的行对象，无数据则为空，需要创建
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
    public List<List<String>> readExcelCell(File file, int sheetIndex, String sheetName) throws Exception {

        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file)); // 读取的文件
        HSSFSheet sheet = null;
        if(StringUtils.isNotEmpty(sheetName)){
            sheetIndex = workbook.getSheetIndex(sheetName); // sheet表名
        }
        sheet = workbook.getSheetAt(sheetIndex);

        for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {// getLastRowNum
            // 获取最后一行的行标
            HSSFRow row = sheet.getRow(j);
            if (row != null) {
                for (int k = 0; k < row.getLastCellNum(); k++) {// getLastCellNum
                    // 是获取最后一个不为空的列是第几个
                    if (row.getCell(k) != null) { // getCell 获取单元格数据

                    }
                }
            }

        }
        return null;
    }
}
