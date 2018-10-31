package StatictisFiledSize;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by zhuhaoju on 2018/2/13.
 */
public abstract class ParentExcelUtil {

    public abstract void insertExcelCell(File file, int sheetIndex, String sheetName, List<RowCellValue> rowCellValues) throws Exception;

    public abstract List<List<String>> readExcelCell(File file, int sheetIndex, String sheetName) throws Exception;
}
