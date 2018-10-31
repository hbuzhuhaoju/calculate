package StatictisFiledSize;


/**
 * Created by zhuhaoju on 2018/2/13.
 */
public class RowCellValue {

    private int rowIndex;

    private int cellIndex;

    private String value;

    public RowCellValue(String value) {
        this.value = value;
    }

    public RowCellValue(int rowIndex, String value) {
        this.rowIndex = rowIndex;
        this.value = value;
    }

    public RowCellValue(int rowIndex, int cellIndex, String value) {
        this.rowIndex = rowIndex;
        this.cellIndex = cellIndex;
        this.value = value;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getCellIndex() {
        return cellIndex;
    }

    public void setCellIndex(int cellIndex) {
        this.cellIndex = cellIndex;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
